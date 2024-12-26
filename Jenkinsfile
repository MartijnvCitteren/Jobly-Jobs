pipeline {
    agent any
    tools{
        maven 'Maven 3.9.9'
        dockerTool 'Docker'
    }
    environment {
            AWS_CLI_PATH = '/opt/homebrew/bin/aws'
            ECR_REGISTRY = '481665105260.dkr.ecr.eu-west-1.amazonaws.com'
            DOCKER_PATH = '/opt/homebrew/bin/docker'
        }
    stages {
        stage('Build Maven') {
            steps {
                checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/MartijnvCitteren/Jobly-Jobs']])
                sh 'mvn clean install'
            }
        }

        stage('Unit Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Logging into AWS ECR') {
                steps {
                    withCredentials([[$class: 'UsernamePasswordMultiBinding',
                        credentialsId: 'aws-ecr-credentials',
                        usernameVariable: 'AWS_ACCESS_KEY_ID',
                        passwordVariable: 'AWS_SECRET_ACCESS_KEY']])  {
                                script {

                sh """
                    mkdir -p /tmp/.docker
                    echo '{"credsStore":""}' > /tmp/.docker/config.json
                """

                withEnv(['DOCKER_CONFIG=/tmp/.docker']) {
                    sh """
                        ${AWS_CLI_PATH} ecr get-login-password --region eu-west-1 | \
                        ${DOCKER_PATH} login \
                            --username AWS \
                            --password-stdin \
                            ${ECR_REGISTRY}
                    """
                }
                    }
                }
            }
        }
        stage('Build Docker Image') {
            steps {
                withCredentials([[$class: 'UsernamePasswordMultiBinding',
                    credentialsId: 'aws-ecr-credentials',
                    usernameVariable: 'AWS_ACCESS_KEY_ID',
                    passwordVariable: 'AWS_SECRET_ACCESS_KEY']]) {
                    sh """
                        mvn compile jib:build \
                        -Djib.to.image=${ECR_REGISTRY}/jobly/jobs:latest \
                        -Djib.to.auth.username=AWS \
                        -Djib.to.auth.password=\$(${AWS_CLI_PATH} ecr get-login-password --region eu-west-1)
                    """
                }
            }
        }
    }
    post {
        always {
            junit '/target/surefire-reports/*.xml'
            allowEmptyResults true
            archiveArtifacts artifacts: '/target/*.jar', allowEmptyArchive: true
        }
    }
}
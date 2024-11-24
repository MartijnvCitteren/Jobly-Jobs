pipeline {
    agent any
    tools{
        maven 'Maven 3.9.9'
        dockerTool 'Docker'
    }
    environment {
            ECR_REGISTRY = '481665105260.dkr.ecr.eu-west-1.amazonaws.com'
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
        stage('Build Docker Image') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'docker-hub-credentials',
                    usernameVariable: 'DOCKER_USERNAME',
                    passwordVariable: 'DOCKER_PASSWORD'
                )]) {
                    sh '''
                        mvn compile jib:build \
                        -Djib.to.auth.username=${DOCKER_USERNAME} \
                        -Djib.to.auth.password=${DOCKER_PASSWORD}
                    '''
                }
            }
        }
        stage('Logging into AWS ECR') {
                    steps {
                        withCredentials([[$class: 'UsernamePasswordMultiBinding',
                                            credentialsId: 'aws-ecr-credentials', u
                                            usernameVariable: 'AWS_ACCESS_KEY_ID',
                                            passwordVariable: 'AWS_SECRET_ACCESS_KEY']])  {
                            sh "aws ecr get-login-password --region eu-west-1 | docker login --username AWS --password-stdin ${ECR_REGISTRY}"
                        }
                    }
                }
        stage('Push to AWS ECR') {
            steps {
                script {
                    sh '''
                        aws ecr get-login-password --region eu-west-1 | docker login --username AWS --password-stdin ${ECR_REGISTRY}
                        docker tag jobly-jobs:latest 481665105260.dkr.ecr.eu-west-1.amazonaws.com/jobly/jobs:latest
                        docker push 481665105260.dkr.ecr.eu-west-1.amazonaws.com/jobly/jobs:latest
                    '''

                }
            }
        }
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
    }
}
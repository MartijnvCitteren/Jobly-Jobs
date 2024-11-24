pipeline {
    agent any
    tools{
        maven 'Maven 3.9.9'
        dockerTool 'Docker'
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
    }
    post {
        always {
            junit '**/target/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/*.jar', allowEmptyArchive: true
        }
    }
}
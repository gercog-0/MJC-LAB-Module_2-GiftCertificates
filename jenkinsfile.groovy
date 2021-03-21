#!groovy
properties([disableConcurrentBuilds(), pipelineTriggers([githubPush()])])

pipeline {
    agent any

    triggers {
        pollSCM('* * * * *')
    }

    stages {
        stage("Checkout") {
            steps {
                git branch: 'module-4', changelog: true, poll: true,
                        url: 'https://github.com/gercog-0/MJC-School-GiftCertificate.git'
            }
        }

        stage("Build, Test") {
            steps {
                script {
                    try {
                        bat 'gradle clean build codeCoverageReport'
                    } finally {
                        junit '**/build/test-results/**/*.xml'
                    }
                }
            }
        }

        stage("SonarQube") {
            environment {
                scannerHome = tool 'sonarqube'
            }
            steps {
                withSonarQubeEnv('sonarqube') {
                    bat "${scannerHome}\\bin\\sonar-scanner"
                }
            }
        }

        stage("Deploy") {
            steps {
                deploy adapters: [tomcat9(credentialsId: 'tomcat',
                        path: '', url: 'http://localhost:8080/')],
                        contextPath: '/', onFailure: false, war: '**/*.war'
            }
        }
    }

    post {
        always {
            echo 'Build completed'
        }
        success {
            echo 'Build successful'
        }
        failure {
            echo 'Build failed'
        }
    }
}
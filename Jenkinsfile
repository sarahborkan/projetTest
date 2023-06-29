pipeline {
    agent any
//test
    tools {
        // Install the Maven version configured as "M3" and add it to the path.,,
        maven "M3"
    }

    stages {
        stage('Build') {
            steps {
              
                // To run Maven on a Windows agent, use
                bat "mvn clean package"
            }

            post {
                // If Maven was able to run the tests, even if some of the test
                // failed, record the test results and archive the jar file.
                success {
                    bat "echo réussit"
                }
            }
        }
        
        stage('Test') {
            steps {
                bat "mvn test"
            }
        }
        
        stage('Install') {
            steps {
                bat "mvn install"
            }
        }
        
    }
}

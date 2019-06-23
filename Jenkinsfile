pipeline {
    agent any
    stages {

       stage('Run tests') {
           steps {
               checkout scm
            }
       }

        stage('Features') {
            steps {
                //Download feature files
                assertthatBddFeatures(credentialsId: '10005-creds', jql: 'project=DEMO', mode: 'automated', outputFolder: 'src/test/resources/com/assertthat/features', projectId: '10005')
            }
        }
        stage('Run tests') {
            steps {
                bat "mvn verify"
            }
        }

    }
    post{
        always{
                //Upload test results
                assertthatBddReport(credentialsId: '10005-creds', jsonReportFolder: 'report', jsonReportIncludePattern: '**/*.json', projectId: '10005', runName: 'Smoke test run', type: 'cucumber')
        }
    }
}
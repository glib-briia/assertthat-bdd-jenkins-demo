pipeline {
    agent any
    stages {

       stage('Checkout') {
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
                assertthatBddReport(credentialsId: '10005-creds', jsonReportFolder: 'target/report/surefire-reports/cucumber', jsonReportIncludePattern: '**/*.json', projectId: '10005', runName: 'Smoke test run', type: 'cucumber')
        }
    }
}
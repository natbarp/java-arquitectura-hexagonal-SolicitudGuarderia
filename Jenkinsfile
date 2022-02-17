@Library('ceiba-jenkins-library')_

pipeline {
  agent {
    label 'Slave_Induccion'
  }


  options {
    	buildDiscarder(logRotator(numToKeepStr: '3'))
 	disableConcurrentBuilds()
  }

  tools {
    jdk 'JDK8_Centos'
  }

  stages{
    stage('Checkout') {
      steps{
        echo "------------>Checkout<------------"
        checkout scm
      }
    }

    stage('Compile & Unit Tests') {
      steps{
        echo "------------>Compile & Unit Tests<------------"
        sh 'chmod +x ./microservicio/gradlew'
        sh './microservicio/gradlew --b ./microservicio/build.gradle clean'
        sh './microservicio/gradlew --b ./microservicio/build.gradle test'

      }
    }

    stage('Static Code Analysis') {
      steps{
        echo '------------>Análisis de código estático<------------'
        sonarqubeMasQualityGatesP(sonarKey:'co.com.ceiba.adn:natalia.GuarderiaMascotas-natalia.barbosa',
                                     sonarName:'CeibaADN-GuarderiaMascotas-natalia.barbosa',
                                     sonarPathProperties:'./sonar-project.properties')
        }
      }


    stage('Build') {
      steps {
        echo "------------>Build<------------"
        sh 'chmod +x ./microservicio/gradlew'
        sh './microservicio/gradlew --b ./microservicio/build.gradle clean'
        sh './microservicio/gradlew --b ./microservicio/build.gradle build -x test'
      }
    }
  }

  post {
    always {
      echo 'This will always run'
    }
    success {
      echo 'This will run only if successful'
//       junit 'microservicio/build/test-results/test/*.xml'
    }
    failure {
      echo 'This will run only if failed'
      mail (to: 'natalia.barbosa@ceiba.com.co',subject: "Failed Pipeline:${currentBuild.fullDisplayName}",body: "Something is wrong with ${env.BUILD_URL}")
    }
    unstable {
      echo 'This will run only if the run was marked as unstable'
    }
    changed {
      echo 'This will run only if the state of the Pipeline has changed'
      echo 'For example, if the Pipeline was previously failing but is now successful'
    }
  }
}
# Example project
* Project : Gradle(Kotlin)
* Language : Kotlin
* Spring Boot : 3.1.4
* Project Metadata
  - Group : com.example
  - Artifact : example
  - Name : example
  - Description : Example project for Spring Boot
  - Package name : com.example
  - Packaging : Jar
  - Java : 17

## How to add example in spring boot
* step 1 - [download initialized project](https://start.spring.io/#!type=gradle-project-kotlin&language=kotlin&platformVersion=3.1.4&packaging=jar&jvmVersion=17&groupId=com.example&artifactId=example&name=example&description=Example%20project%20for%20Spring%20Boot&packageName=com.example&dependencies=web)
* step 2 - click 'GENERATE' button
* step 3 - upzip the 'example.zip'
* step 4 - move the 'example' folder to spring boot project folder
* step 5 - delete gradlew, gradle folder, gradlew.bat, .gitignore
  - <code>$ rm -rf .gitignore gradle gradlew gradlew.bat settings.gradle.kts</code>
* step 6 - add <code>include("example")</code> in settings.gradle.kts file**

## How to set Swagger to Spring boot 3 - [link](https://delicate-walrus-052.notion.site/Swagger-with-Spring-boot-3-x-4ab2fa1b158545058accdf393d127e8f?pvs=4)
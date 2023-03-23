**# First example project
* Project : Gradle(Kotlin)
* Language : Kotlin
* Spring Boot : 3.0.4
* Project Metadata
  - Group : com.example.first
  - Artifact : example-first
  - Name : example-first
  - Description : First example project for Spring Boot
  - Package name : com.example.first
  - Packaging : Jar
  - Java : 17

## How to add first example in spring boot
* step 1 - [download initialized project](https://start.spring.io/#!type=gradle-project-kotlin&language=kotlin&platformVersion=3.0.4&packaging=jar&jvmVersion=17&groupId=com.example.first&artifactId=example-first&name=example-first&description=First%20example%20project%20for%20Spring%20Boot&packageName=com.example.first&dependencies=web)
* step 2 - click 'GENERATE' button
* step 3 - upzip the 'example-first.zip'
* step 4 - move the 'example-first' folder to spring boot project folder
* step 5 - delete gradlew, gradle folder, gradlew.bat, .gitignore
  - <code>$ rm -rf .gitignore gradle gradlew gradlew.bat settings.gradle.kts</code>
* step 6 - add <code>include("example-first")</code> in settings.gradle.kts file**
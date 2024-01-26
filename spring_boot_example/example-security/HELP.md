# Security example project
* Project : Gradle(Kotlin)
* Language : Kotlin
* Spring Boot : 3.2.2
* Project Metadata
    - Group : com.example.security
    - Artifact : example-security
    - Name : example-security
    - Description : security example project for Spring Boot
    - Package name : com.example.security
    - Packaging : Jar
    - Java : 17

## How to add security example in spring boot
* step 1 - [download initialized project](https://start.spring.io/#!type=gradle-project-kotlin&language=kotlin&platformVersion=3.2.2&packaging=jar&jvmVersion=17&groupId=com.example.security&artifactId=example-security&name=example-security&description=Security%20example%20project%20for%20Spring%20Boot&packageName=com.example.security&dependencies=web,security,data-jpa,mariadb,lombok)
* step 2 - click 'GENERATE' button
* step 3 - upzip the 'example-security.zip'
* step 4 - move the 'example-security' folder to spring boot project folder
* step 5 - delete gradlew, gradle folder, gradlew.bat, .gitignore
    - <code>$ rm -rf .gitignore gradle gradlew gradlew.bat settings.gradle.kts</code>
* step 6 - add <code>include("example-security")</code> in settings.gradle.kts file

## JWT Authentication and Authorisation
### Refer to
* Youtube - https://www.youtube.com/watch?v=KxqlJblhzfI&t=3739s
* github - https://github.com/ali-bouali/spring-boot-3-jwt-security

### 1. Create User entity 
refer to `User.kt` file

implement UserDetails to User class


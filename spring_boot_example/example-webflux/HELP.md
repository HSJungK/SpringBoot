# WebFlux example project
* Project : Gradle(Kotlin)
* Language : Kotlin
* Spring Boot : 3.1.4
* Project Metadata
    - Group : com.example.webflux
    - Artifact : example-webflux
    - Name : example-webflux
    - Description : webflux example project for Spring Boot
    - Package name : com.example.webflux
    - Packaging : Jar
    - Java : 17

## Web Flux
* [English](./md/StringWebFluxEN.md)
* [Korean](./md/StringWebFluxKO.md)

## How to add webflux example in spring boot
* step 1 - [download initialized project](https://start.spring.io/#!type=gradle-project-kotlin&language=kotlin&platformVersion=3.1.4&packaging=jar&jvmVersion=17&groupId=com.example.webflux&artifactId=example-webflux&name=example-webflux&description=WebFlux%20example%20project%20for%20Spring%20Boot&packageName=com.example.webflux&dependencies=webflux)
* step 2 - click 'GENERATE' button
* step 3 - upzip the 'example-webflux.zip'
* step 4 - move the 'example-webflux' folder to spring boot project folder
* step 5 - delete gradlew, gradle folder, gradlew.bat, .gitignore
    - <code>$ rm -rf .gitignore gradle gradlew gradlew.bat settings.gradle.kts</code>
* step 6 - add <code>include("example-webflux")</code> in settings.gradle.kts file
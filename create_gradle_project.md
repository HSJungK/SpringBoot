# How to create init this project
## step 1. gradle install
* mac - <code>$ brew update && brew install gradle</code>

## step 2. create project
<pre><code>
$ gradle init
Welcome to Gradle 8.0.1!

Here are the highlights of this release:
 - Improvements to the Kotlin DSL
 - Fine-grained parallelism from the first build with configuration cache
 - Configurable Gradle user home cache cleanup

For more details see https://docs.gradle.org/8.0.1/release-notes.html

Starting a Gradle Daemon (subsequent builds will be faster)

Select type of project to generate:
  1: basic
  2: application
  3: library
  4: Gradle plugin
Enter selection (default: basic) [1..4] 1

Select build script DSL:
  1: Groovy
  2: Kotlin
Enter selection (default: Groovy) [1..2] 2

Generate build using new APIs and behavior (some features may change in the next
Project name (default: spring_boot): 

> Task :init
Get more help with your project: Learn more about Gradle by exploring our samples at https://docs.gradle.org/8.0.1/sample
</code></pre>

## step 3. Open IDE
* IntelliJ - https://www.jetbrains.com/ko-kr/idea/download
* Spring Tools - https://spring.io/tools
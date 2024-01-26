plugins {
    kotlin("jvm") version "1.8.22"
}

group = "com.example.lib"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {

    implementation("com.fasterxml.jackson.core:jackson-annotations:2.0.1")


    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
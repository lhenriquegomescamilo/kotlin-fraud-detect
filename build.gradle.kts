plugins {
    kotlin("jvm") version "2.1.21"
}

group = "com.camilo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jgrapht:jgrapht-core:1.5.2")
    testImplementation("io.kotest:kotest-runner-junit5:5.9.1")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
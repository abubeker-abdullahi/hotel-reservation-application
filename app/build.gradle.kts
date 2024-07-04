plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application

    // Apply the JaCoCo plugin for code coverage
    id("jacoco")
}

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    // Use JUnit Jupiter for testing.
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

    // This dependency is used by the application.
    implementation("com.google.guava:guava:31.0.1-jre") // Ensure libs.guava is defined or use specific version

    // Mockito for mocking in tests
    testImplementation("org.mockito:mockito-core:4.8.0")
    testImplementation("org.mockito:mockito-junit-jupiter:4.8.0")

    // Kotlin test dependency
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:1.7.10")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

application {
    // Define the main class for the application.
    mainClass.set("com.example.MainClass")
}

tasks.test {
    // Enable JaCoCo for code coverage
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(false)
        csv.required.set(false)
        html.outputLocation.set(file("$buildDir/reports/jacoco"))
    }
}

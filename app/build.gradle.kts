plugins {
    // Apply the application plugin to add support for building a CLI application in Java.
    application

    // Apply the JaCoCo plugin for code coverage
    id("jacoco")

    // Apply the Maven Publish plugin for publishing artifacts
    id("maven-publish")
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

// Maven Publish configuration
publishing {
    publications {
        create<MavenPublication>("gpr") {
            from(components["java"])
            groupId = "com.example"
            artifactId = "my-app"
            version = "1.0.0"
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/abubeker-abdullahi/hotel-reservation-application")
            credentials {
                username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
                password = project.findProperty("gpr.token") as String? ?: System.getenv("TOKEN")
            }
        }
    }
}
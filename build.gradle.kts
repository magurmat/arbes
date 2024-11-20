plugins {
    id("java-library")
    id("maven-publish")
}

group = "com.phonecompany.billing"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.test {
    useJUnitPlatform()
}

tasks.build {
    dependsOn(tasks.test)
}

tasks.publishToMavenLocal {
    dependsOn(tasks.test)
}

tasks.publish {
    dependsOn(tasks.test)
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
    repositories {
        maven {
            url = uri(buildDir.resolve("repo"))
        }
    }
}
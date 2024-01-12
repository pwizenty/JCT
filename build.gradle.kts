plugins {
    id("java")
    `maven-publish`
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(files("lib/de.fhdo.lemma.data.datadsl-0.8.6-SNAPSHOT-assembly.jar"))
    implementation("org.jolie-lang:jolie:1.10.4")
    implementation("org.jolie-lang:libjolie:1.10.4")
    implementation("org.jolie-lang:jolie-cli:1.10.4")
}

tasks.test {
    useJUnitPlatform()
}
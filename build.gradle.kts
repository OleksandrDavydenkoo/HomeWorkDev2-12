import org.gradle.internal.impldep.org.bouncycastle.cms.RecipientId.password

plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.3"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("org.hibernate.orm:hibernate-core:6.5.2.Final")
    implementation ("org.flywaydb:flyway-core:10.15.2")
    implementation ("org.postgresql:postgresql:42.6.3")
    implementation ("jakarta.validation:jakarta.validation-api:3.0.0")
    compileOnly ("org.projectlombok:lombok:1.18.34")
    annotationProcessor ("org.projectlombok:lombok:1.18.34")

}

tasks.test {
    useJUnitPlatform()
}

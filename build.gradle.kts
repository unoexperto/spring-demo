import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val springBoot = "2.7.0-SNAPSHOT"

plugins {
//	id("org.springframework.boot") version "2.7.0-SNAPSHOT"
//	id("io.spring.dependency-management") version "1.0.11.RELEASE"
//	kotlin("plugin.spring") version "1.6.10"

    kotlin("jvm") version "1.6.10"
//	kotlin("kapt") version "1.6.10" // used by dsl-json
    application //to run JVM part
    `java-library`
    idea
    `kotlin-dsl` //version "2.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11
java.targetCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    implementation(kotlin("stdlib", "1.6.10"))
    implementation(kotlin("reflect", "1.6.10"))

    implementation("org.springframework.boot:spring-boot-starter-data-jdbc:$springBoot") {
        exclude("org.springframework.boot", "spring-boot-starter-logging")
    }
    implementation("org.springframework.boot:spring-boot-starter-webflux:$springBoot") {
        exclude("org.springframework.boot", "spring-boot-starter-logging")
    }
//	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
//	implementation("org.jetbrains.kotlin:kotlin-reflect")
//	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
//	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

    implementation("com.h2database:h2:2.1.210")
    implementation("org.jdbi:jdbi3-core:3.28.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBoot")
//	testImplementation("io.projectreactor:reactor-test")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
        apiVersion = "1.6"
        languageVersion = "1.6"
    }
}

//tasks.withType<Test> {
//	useJUnitPlatform()
//}

tasks {
    wrapper {
        gradleVersion = "7.3.1"
        distributionType = Wrapper.DistributionType.BIN
    }
}

application {
    mainClass.set("com.example.demo.DemoApplication")
//    mainClassName = "com.bitparticles.EntryPointKt"
//    mainClassName = "com.bitparticles.engine.EngineKt"
//    applicationDefaultJvmArgs = listOf("--enable-preview")
}

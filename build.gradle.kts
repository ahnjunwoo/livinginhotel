import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.7.22"
    id("org.asciidoctor.convert") version "2.4.0"
    id("org.jetbrains.kotlin.plugin.noarg") version "1.7.21"
    id("com.epages.restdocs-api-spec") version "0.17.1"
}
val kotestVersion = "5.4.2"
group = "com"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.modelmapper:modelmapper:3.1.0")
    implementation("org.springframework.boot:spring-boot-starter-validation:3.0.5")
    implementation("io.github.microutils:kotlin-logging-jvm:2.1.23")
    runtimeOnly("com.mysql:mysql-connector-j")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.kotest.extensions:kotest-extensions-spring:1.1.2")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.mockk:mockk:1.12.5")
    testImplementation("com.ninja-squad:springmockk:3.1.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.9.0")

    testImplementation("com.epages:restdocs-api-spec-mockmvc:0.17.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.getByName<BootJar>("bootJar") {
    this.archiveFileName.set("livinginhotel.${archiveExtension.get()}")
}

noArg {
    annotations("com.livinginhotel.common.NoArg")
    annotations("com.livinginhotel.common.Domain")
}

openapi3 {
    this.outputFileNamePrefix = "hotel"
    title = "hotel booking service"
    description = "hotel booking API description"
    version = "0.0.1"
    format = "yaml" // or json
}

tasks.register<Copy>("copyOasToSwagger") {
    val fileName = "${openapi3.outputFileNamePrefix}.${openapi3.format}"
    val destDir = "src/main/resources/static/swagger-ui/"

    delete("$destDir/$fileName") // 기존 yaml 파일 삭제
    from("$buildDir/api-spec/$fileName") // 복제할 yaml 파일 타겟팅
    into("$destDir/.") // 타겟 디렉토리로 파일 복제

    dependsOn("openapi3") // openapi3 task가 먼저 실행되도록 설정
}
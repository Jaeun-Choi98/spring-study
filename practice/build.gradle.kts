plugins {
	java
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "juchoi.study"
version = "0.0.1-SNAPSHOT"
description = "Demo project for Spring Boot"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-configuration-processor")
	implementation("org.springframework.boot:spring-boot-starter-actuator")

	// Spring Data JPA
  implementation ("org.springframework.boot:spring-boot-starter-data-jpa")

	// Spring JDBC
  implementation ("org.springframework.boot:spring-boot-starter-jdbc")

  runtimeOnly("com.mysql:mysql-connector-j")
	compileOnly("org.projectlombok:lombok")
  annotationProcessor("org.projectlombok:lombok")

	// MOM
	// Spring Cloud Stream
  implementation ("org.springframework.cloud:spring-cloud-stream")
  // RabbitMQ Binder
  implementation ("org.springframework.cloud:spring-cloud-stream-binder-rabbit")

	developmentOnly("org.springframework.boot:spring-boot-devtools")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:2023.0.0")
    }
}

tasks.withType<Test> {
	useJUnitPlatform()
}

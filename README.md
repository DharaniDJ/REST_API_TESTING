# REST Assured API Testing with Gradle

### Overview
This project demonstrates API testing using **REST Assured**, a powerful Java library for testing RESTful web services. It includes automated test cases for validating API endpoints with **JUnit/TestNG**, JSON validation, authentication handling, and response assertions.

### 🔹 Features
- **Automated API Testing** – Validate RESTful APIs using REST Assured
- **JSON/XML Response Validation** – Verify response structure and data
- **Authentication Handling** – Supports OAuth, JWT, Basic, and Digest authentication
- **Parameterized Tests** – Reuse test cases with different data sets
- **Assertions & Logging** – Validate response status, headers, and body
- **CI/CD Ready** – Easily integrate with Jenkins, GitHub Actions, or any CI/CD pipeline

### Tech Stack
- **Java** (JDK 11+)
- **REST Assured**
- **JUnit 5 / TestNG**
- **Gradle** (Kotlin DSL or Groovy)

### Gradle Dependencies
For Gradle Groovy DSL (build.gradle):

```gradle
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testImplementation 'io.rest-assured:rest-assured:5.5.0'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
	testImplementation 'org.junit.jupiter:junit-jupiter-api:5.11.4'
    testImplementation 'org.hamcrest:hamcrest:3.0'
	testImplementation 'org.junit.jupiter:junit-jupiter-params:5.11.4'
	testImplementation 'org.testng:testng:7.10.2'
	implementation 'com.fasterxml.jackson.core:jackson-databind:2.18.2'
	implementation 'io.rest-assured:json-schema-validator:5.5.0'
}
```

### Getting Started

1️. Clone the repo:
   ```sh
   git clone https://github.com/your-username/rest-assured-api-testing.git
   cd rest-assured-api-testing
   ```

2️. Build the project:
   ```sh
   ./gradlew build
   ```

3️. Run the tests:
   ```sh
   ./gradlew test
   ```

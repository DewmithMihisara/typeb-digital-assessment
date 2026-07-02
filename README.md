# Take Home Assessment

This project is a simple REST API built with **Spring Boot 4.1** and **Java 21**. It exposes a single endpoint that returns a greeting or an error based on the first letter of the provided name.

---

## API Endpoint

### `GET /hello-world?name={name}`

| Input | Response |
|-------|----------|
| Name starts with **A–M** (case-insensitive) | **200 OK** → `{ "message": "Hello <Name>" }` |
| Name starts with **N–Z** (case-insensitive) | **400 Bad Request** → `{ "error": "Invalid Input" }` |
| Name is missing, empty, or only whitespace | **400 Bad Request** → `{ "error": "Invalid Input" }` |

---

## Running the Application

### Prerequisites

- Java 21 or later
- Maven (or use the included Maven Wrapper)

### Start the application

**macOS / Linux**

```bash
./mvnw spring-boot:run
```

**Windows**

```bash
mvnw.cmd spring-boot:run
```

Once the application starts, it will be available at:

```
http://localhost:8080
```

---

## Example Requests

### Valid request

```bash
curl "http://localhost:8080/hello-world?name=alice"
```

Response

```json
{
  "message": "Hello Alice"
}
```

---

### Invalid request (starts with N–Z)

```bash
curl "http://localhost:8080/hello-world?name=nathan"
```

Response

```json
{
  "error": "Invalid Input"
}
```

---

### Missing name

```bash
curl "http://localhost:8080/hello-world"
```

Response

```json
{
  "error": "Invalid Input"
}
```

---

## API Documentation

Swagger UI is included with the project, making it easy to test the endpoint without using `curl`.

After starting the application, open:

```
http://localhost:8080/swagger-ui.html
```

From there you can:

1. Expand the **Hello World** section.
2. Select **GET /hello-world**.
3. Click **Try it out**.
4. Enter a value for `name`.
5. Click **Execute** to see the response.

The generated OpenAPI specification is also available at:

```
http://localhost:8080/v3/api-docs
```

---

## Running the Tests

Run all tests using:

```bash
./mvnw test
```

The project includes:

- **HelloWorldServiceTest** – Unit tests covering the business logic, including valid names, boundary cases (M/N), blank input, and invalid characters.
- **HelloWorldControllerTest** – `MockMvc` tests that verify the API responses, HTTP status codes, and JSON payloads.

---

## Design Decisions & Assumptions

A few implementation choices were made while keeping the assignment requirements in mind:

- The first character of the name determines whether the request is valid, and the check is **case-insensitive**.
- The returned greeting capitalizes only the first character of the provided name. For example:
  - `alice` → `Hello Alice`
  - `aLICE` → `Hello ALICE`
- Requests where `name` is missing, empty, or contains only whitespace return **400 Bad Request**.
- If the first character is not an alphabetic letter (for example, a digit or symbol), the request is also treated as invalid and returns **400 Bad Request**.

---

## Technology Stack

- Java 21
- Spring Boot 4.1
- Spring Web
- springdoc OpenAPI (Swagger UI)
- JUnit 5
- MockMvc
- Maven

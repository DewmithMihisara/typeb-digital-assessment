# take-home-assessment

A small Spring Boot HTTP API with a single endpoint, `GET /hello-world`.

## Endpoint

`GET /hello-world?name={name}`

| Condition | Status | Body |
| --- | --- | --- |
| First letter of `name` is A-M / a-m | 200 | `{ "message": "Hello <Name>" }` |
| First letter of `name` is N-Z / n-z | 400 | `{ "error": "Invalid Input" }` |
| `name` is missing, empty, or whitespace-only | 400 | `{ "error": "Invalid Input" }` |

## How to run

Requires JDK 21+.

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The app starts on `http://localhost:8080`. Example requests:

```bash
curl "http://localhost:8080/hello-world?name=alice"
# 200 {"message":"Hello Alice"}

curl "http://localhost:8080/hello-world?name=nathan"
# 400 {"error":"Invalid Input"}

curl "http://localhost:8080/hello-world"
# 400 {"error":"Invalid Input"}
```

## Trying it out with Swagger UI

An interactive OpenAPI/Swagger UI (via springdoc) is bundled with the app, so you can exercise the endpoint from a browser instead of `curl`:

1. Start the app (see above) and leave it running.
2. Open `http://localhost:8080/swagger-ui.html`.
3. Expand the **Hello World** tag, then `GET /hello-world`.
4. Click **Try it out**, enter a value in the `name` field (e.g. `alice`), and click **Execute**.
5. Check the **Response body** and **Server response code**:
   - A name starting with A–M/a–m (e.g. `alice`) → `200` with `{ "message": "Hello Alice" }`.
   - A name starting with N–Z/n–z (e.g. `nathan`) → `400` with `{ "error": "Invalid Input" }`.
   - Leave `name` blank and execute → `400` with `{ "error": "Invalid Input" }`.

The raw OpenAPI spec is also available at `http://localhost:8080/v3/api-docs` if you want to inspect it directly or import it into another tool (e.g. Postman).

## How to run the tests

```bash
./mvnw test
```

This runs:
- `HelloWorldServiceTest` — plain unit tests for the greeting/validation logic (valid names, boundary letters M/N, missing/empty/whitespace input, non-letter first characters).
- `HelloWorldControllerTest` — `@WebMvcTest`/`MockMvc` tests exercising the HTTP layer (status codes and JSON body shape) for the same cases.

## Assumptions

- Validity is determined only by the first character of `name`, case-insensitively.
- Only the first character of the returned name is case-normalized (uppercased) to match the greeting example (`alice` → `Hello Alice`); the rest of the input is passed through unchanged (e.g. `aLICE` → `Hello ALICE`).
- A `name` that is missing, empty, or made up entirely of whitespace is treated as invalid input (`400`), per the spec's "missing or empty" rule extended to blank strings.
- A first character that isn't a letter (a digit, symbol, etc.) is treated as invalid input (`400`), since it falls outside both the A–M and N–Z ranges described in the spec.

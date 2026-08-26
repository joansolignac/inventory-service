# Repository Guidelines

## Project Structure & Module Organization

This is a Gradle-based Spring Boot inventory service. Application code lives in
`src/main/java/com/joan/inventoryservice`. Shared concerns belong in `common/`
(configuration, DTOs, exceptions, and commands); business code is organized by
domain in `modules/`, currently `category/` and `product/`. Within a module,
keep HTTP controllers, request/response DTOs, entities, repositories, feature
classes, helpers, and exceptions in their matching subpackages. Runtime
configuration is in `src/main/resources/application*.yaml`; tests mirror the
production package layout under `src/test/java`.

## Build, Test, and Development Commands

Run commands from the repository root on Windows:

- `./gradlew.bat bootRun` — start the service on port 8080.
- `./gradlew.bat test` — execute the JUnit Platform test suite.
- `./gradlew.bat build` — compile, test, and produce the Spring Boot artifact.
- `docker compose up -d` — start MySQL (host port 3308) and Redis (6379).
- `docker compose down` — stop local dependencies; add `-v` only when a fresh
  database is intentionally required.

Use the Gradle wrapper, not a machine-installed Gradle version. The build
targets Java 25, so configure a compatible JDK before compiling.

## Coding Style & Naming Conventions

Use four-space indentation, braces on the declaration line, and standard Java
package names in lowercase. Classes, records, and enums use `PascalCase`;
methods and fields use `camelCase`; constants use `UPPER_SNAKE_CASE`. Name
application actions as focused feature classes such as `CreateCategory` or
`FindAllProducts`, and use `*Request`/`*Response` for API payloads. Prefer
constructor injection with Lombok's `@RequiredArgsConstructor`; validate
incoming DTOs with Jakarta Validation and route domain failures through the
shared exception handling layer. No formatter or linter is configured—match
the surrounding code and keep imports tidy.

## Testing Guidelines

Tests use JUnit 5 and Spring Boot test starters. Place a test beside its target
package and name it `*Tests` (for example, `CategoryControllerTests`). Use
descriptive test methods such as `createRejectsBlankCategoryName`. Add focused
tests for new validation, feature behavior, repository queries, and error
responses; run `./gradlew.bat test` before submitting. No coverage threshold
is configured, but new behavior should be covered.

## Commit & Pull Request Guidelines

The history currently contains only `Initial commit`, so no project-specific
commit pattern exists yet. Use short imperative subjects, optionally scoped:
`feat(category): add update endpoint` or `fix(product): reject duplicate SKU`.
Keep commits cohesive. Pull requests should explain the change and verification,
link the relevant issue when available, call out configuration or schema
effects, and include request/response examples for API changes.

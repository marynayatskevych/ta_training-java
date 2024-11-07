# ta_training-java

## Project Structure

The project follows a standard Maven structure:

- **`src/main`**: not used, but would contain application code if needed.
- **`src/test/java`**: contains all Java test classes, including step definitions, page objects, and test runners.
- **`src/test/resources`**: contains Cucumber feature files for writing test scenarios in Gherkin.
- **`pom.xml`**: configuration file for Maven, managing dependencies and build settings.
 
### `DriverSingleton` 
- class is responsible for managing the lifecycle of `WebDriver` instances in the project.

Key Features:
- **Singleton Pattern**: ensures only one instance of `WebDriver` is created per thread.
- **Thread Safety**: uses `ThreadLocal` to provide each thread with its own instance of `WebDriver`, enabling parallel execution without resource conflicts.
- **Browser Management**: supports different browsers (`Chrome`, `Edge`) depending on the system property specified (`browser`).
- **Automatic Setup**: utilizes WebDriverManager for automatically handling the setup of browser drivers.



  ### `BasePage`

- class provides a foundation for other page objects like `LoginPage` by encapsulating common operations and centralizing WebDriver usage.

Key Features:
- **Error Messages**: defines common error messages (`ERROR_USERNAME_MESSAGE` and `ERROR_PASSWORD_MESSAGE`) that can be reused in page objects and tests to validate error scenarios.
- **Logging**: uses SLF4J (`Logger`) for logging purposes, helping in tracking actions and debugging issues across the project.
- **WebDriver Management**: holds an instance of `WebDriver` that is shared across all derived page objects.

### `LoginPage`

- class represents the login page of the application and extends the `BasePage` to utilize common functionalities.

Key Features:

- The `LoginPage` class is used for executing actions on the login page, such as entering credentials, clearing fields, clicking login, and fetching error messages. It encapsulates all login page interactions, improving readability and maintaining the single-responsibility principle in testing.
- **Page Initialization**: Uses `PageFactory.initElements()` to initialize WebElements.

### `User`

- class represents user credentials, specifically the username and password.

Key Features:
- **Immutable Fields**: The class uses immutable fields (`username` and `password`), ensuring user data remains consistent throughout the test run.
- **Builder Pattern**: The `Builder` nested class allows flexible and readable creation of `User` objects with different attributes.

### `LoginSteps`

- class provides step definitions for Cucumber test scenarios related to the login page. These step definitions map Gherkin feature file steps to executable Java code.

Key Features:
- Implements methods annotated with Cucumber's `@Given`, `@When`, `@And`, and `@Then` annotations, allowing each method to represent specific actions or assertions within test scenarios.
- **Integration with Page Object Model**, uses the `LoginPage` class to interact with elements on the login page.

### `CommonConditions`

- class serves as a base test class for managing WebDriver setup and teardown.

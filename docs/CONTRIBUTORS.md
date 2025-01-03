# Contributor Guide

## Table of Contents
- [Branch Management Policy](#branch-management-policy)
- [Commit Messages Conventions](#commit-messages-conventions)
- [Code Conventions](#code-conventions)
- [Release Policy](#release-policy)
- [Managing Issues and Branches](#managing-issues-and-branches)
- [Continuous Integration](#continuous-integration)
- [Fixing Checkstyle Violations](#fixing-checkstyle-violations)
- [Deployment](#deployment)
- [Telemetry Setup](#telemetry-setup)
- [Contact](#contact)

## Branch Management Policy
- **Branch Naming**: Branches should be named based on the issue they address. For example, `issue-#123-fix-bug` or `feature-add-new-game-mode`.
- **Branch Placement**: All branches should be created from the `main` branch.
- **Merging**: A branch can be merged into the `main` branch after a pull request has been reviewed and approved by at least one other contributor.

## Commit Messages Conventions
- Use clear and descriptive commit messages.
- Follow the format: `type(scope): description`.
    - **Type**: `feat` (feature), `fix` (bug fix), `docs` (documentation), `style` (formatting), `refactor` (refactoring), `test` (adding tests), `chore` (maintenance).
    - **Scope**: The part of the codebase affected (optional).
    - **Description**: A brief summary of the changes.

### Examples
- `feat(game): add new game mode`
- `fix(ui): resolve button alignment issue`

## Code Conventions
- Follow the Java coding standards.
- Use meaningful variable and method names.
- Write comments to explain complex logic.

## Release Policy
- **Release Pattern**: Follow semantic versioning (e.g., `v1.0.0`).
- **Release Frequency**: Aim for a new release every two weeks.

## Managing Issues and Branches
1. **Creating an Issue**: When you identify a task, create an issue in the repository. Provide a clear description of the task.
2. **Creating a Branch**: Create a new branch from the `main` branch. Name the branch based on the issue (e.g., `issue-#123-fix-bug`).
3. **Working on the Branch**: Make your changes in the branch. Commit your changes with clear and descriptive messages.
4. **Creating a Pull Request**: Once your changes are ready, create a pull request. Link the pull request to the issue it addresses.
5. **Review and Merge**: Another contributor should review your pull request. Once approved, the pull request can be merged into the `main` branch.

## Continuous Integration

This project uses GitHub Actions for continuous integration. 
The CI pipeline is defined in the `.github/workflows/ci.yml` file.

### Triggering a Build

The CI pipeline is automatically triggered on the following events:
- Push to the `main` branch
- Pull request to the `main` branch

### Steps in the Pipeline

1. **Checkout repository**: Clones the repository.
2. **Set up JDK 11**: Prepares the Java environment.
3. **Build with Maven**: Compiles and packages the project.
4. **Run tests**: Executes unit tests.
5. **Code coverage**: Generates a coverage report.
6. **Package application**: Packages the application into a JAR file.
7. **Deploy to server**: Deploys the application to a live environment.
8. **Telemetry setup**: Sets up telemetry for monitoring.

## Fixing Checkstyle Violations

### Missing `package-info.java` File
To fix the missing `package-info.java` file for Javadoc:
1. Create a file named `package-info.java` in the `be.unamur.game2048.models` package.
2. Add the following content to the file:
   ```java
   /**
    * This package contains the model classes for the 2048 game.
    */
   package be.unamur.game2048.models;
   ```

### Missing Javadoc Comments for Variables
To fix missing Javadoc comments for variables in `GameState.java`:  
Open the `GameState.java` file.
Add Javadoc comments for each variable. 

### Parameters in `GridHelper.java` Should Be Final
To fix the parameters in `GridHelper.java` that should be `final`:
Open the `GridHelper.java` file.
Add the `final` keyword to the parameters that should be final. For example:
   ```java
   public static boolean tileEqual(final Grid grid, final int row, final int col, final Integer val) {
       return grid.getTile(row, col).equals(new Tile(val));
   }
   ```

### If Constructs in `GridHelper.java` Must Use Braces
To fix the if constructs in `GridHelper.java` that must use braces:
Open the `GridHelper.java` file.
Add braces to the if constructs that are missing them. For example:
    ```java
    if (grid.getTile(row, col).getValue() == val) {
         return true;
    }
    ``` 
### Adding JaCoCo Plugin

To ensure code coverage is measured, the JaCoCo plugin must be configured in the `pom.xml` file. Follow these steps to add the JaCoCo plugin:

1. **Open the `pom.xml` file**.
2. **Add the following configuration** under the `<build>` section:

    ```xml
    <build>
        <plugins>
            <!-- Other plugins -->

            <plugin>
                <groupId>org.jacoco</groupId>
                <artifactId>jacoco-maven-plugin</artifactId>
                <version>0.8.7</version>
                <executions>
                    <execution>
                        <goals>
                            <goal>prepare-agent</goal>
                        </goals>
                    </execution>
                    <execution>
                        <id>report</id>
                        <phase>test</phase>
                        <goals>
                            <goal>report</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
    ```

3. **Commit and push your changes** to the repository to ensure the build passes the code coverage step.

## Deployment

To deploy the application, the CI pipeline automatically copies the JAR file to the server and runs it. Ensure the server details and paths are correctly configured in the workflow file (`.github/workflows/ci.yml`).

## Telemetry Setup

To set up telemetry for monitoring the application, follow these steps:

1. **Choose a Telemetry Tool**: Select a telemetry tool such as Prometheus, Grafana, or any other monitoring tool that suits your needs.

2. **Integrate Telemetry Tool**: Add the necessary dependencies and configurations to your project to integrate the chosen telemetry tool. For example, if using Prometheus, you might need to add a Prometheus client library.

3. **Configure Telemetry in CI Pipeline `.github/workflows/ci.yml`**: Update the CI pipeline file to include steps for setting up telemetry. This might involve running a telemetry agent or configuring the application to send metrics to the telemetry tool.

4. **Add Telemetry Code**: Modify your application code to collect and send metrics. This could involve adding annotations or using APIs provided by the telemetry tool.

### Example: Integrating Prometheus

1. **Add Dependency**: Add the Prometheus client dependency to the `pom.xml` file.
    ```xml
    <dependency>
        <groupId>io.prometheus</groupId>
        <artifactId>simpleclient</artifactId>
        <version>0.11.0</version>
    </dependency>
    <dependency>
        <groupId>io.prometheus</groupId>
        <artifactId>simpleclient_hotspot</artifactId>
        <version>0.11.0</version>
    </dependency>
    <dependency>
        <groupId>io.prometheus</groupId>
        <artifactId>simpleclient_httpserver</artifactId>
        <version>0.11.0</version>
    </dependency>
    ```

2. **Initialize Metrics**: Initialize and expose metrics in the `main.java` class of application. Here is an example of how to do it in a dedicated class named `TelemetrySetup`
    ```java
    import io.prometheus.client.Counter;
    import io.prometheus.client.exporter.HTTPServer;
    import io.prometheus.client.hotspot.DefaultExports;

    public class TelemetrySetup {
        static final Counter requests = Counter.build()
            .name("requests_total").help("Total requests.").register();

        public static void main(String[] args) throws Exception {
            // Expose metrics to Prometheus
            HTTPServer server = new HTTPServer(1234);
            DefaultExports.initialize();
        }

        public void handleRequest() {
            requests.inc();
            // Handle the request
        }
    }
    ```

3. **Update `.github/workflows/ci.yml`**: Ensure the telemetry setup is included in the CI pipeline.
    ```yaml
    - name: Telemetry setup
      run: |
        echo "Setting up telemetry..."
        # Add commands to start telemetry server or agent
        # Example: java -jar telemetry-agent.jar
    ```

By following these steps, you can set up telemetry to monitor the application's performance and health.


## Contact
For any questions or feedback, please open an issue in the repository or contact the project maintainers.
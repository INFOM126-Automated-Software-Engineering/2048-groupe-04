# 2048 Game

## Overview
This repository contains the implementation of the 2048 game. The game is developed in Java and uses Maven for dependency management and build automation.

## How to Play
2048 is a single-player sliding tile puzzle game. The objective is to slide numbered tiles on a grid to combine them and create a tile with the number 2048.

### Controls
- **Up Arrow**: Move tiles up
- **Down Arrow**: Move tiles down
- **Left Arrow**: Move tiles left
- **Right Arrow**: Move tiles right

## Getting Started

### Prerequisites
- Java Development Kit (JDK) 11 or higher
- Maven 3.6.0 or higher

### Installation
1. **Clone the repository**:
    ```sh
    git clone https://github.com/INFOM126-Automated-Software-Engineering/2048GROUPE04.git
    cd 2048GROUPE04
    ```

2. **Build the project**:
    ```sh
    mvn clean install
    ```

### Running the Game
To run the game, execute the following command:
```sh
mvn exec:java -Dexec.mainClass="be.unamur.game2048.Main"
```

## Deployment
To deploy the game, you can package it into a JAR file and run it on any platform with Java installed.

1. **Package the application**:
    ```sh
    mvn package
    ```

2. **Run the JAR file**:
    ```sh
    java -jar target/2048-1.0-SNAPSHOT.jar
    ```

## Using the Code
### Project Structure
- `src/main/java`: Contains the main source code.
- `src/test/java`: Contains the test code.
- `pom.xml`: Maven configuration file.

### Dependencies
The project uses the following dependencies:
- JUnit: For unit testing.
- Maven: For build automation and dependency management.

### Cross-Platform Considerations
The game is developed in Java, which is platform-independent. Ensure you have the correct version of JDK installed on your system.

## Contributing
Please refer to the `docs/CONTRIBUTOR.md` file for guidelines on how to contribute to this project.

## License
This project is licensed under the MIT License. See the `LICENSE` file for more details.

## Contact
For any questions or feedback, please open an issue in the repository or contact the project maintainers. 
 

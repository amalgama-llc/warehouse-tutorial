## How to build

### Prerequisites

1. JavaFX should be installed.
2. The JAVAFX_HOME environmental variable should contain full path to your local JavaFX installation folder.
3. JDK 21 and Maven 3.9.0+ should be installed. Check the output of the `mvn -version` command.

### Build with Maven

Run the following command:
```
mvn clean package
```

Note that the `product` file (in the `releng/<project.name>.product` folder) gets modified during the build.

The build result is located in `releng/<project.name>.product/target/products/`.
The folder contains the application files in the form of a folder and a `zip` file.

To start the application, go into the
folder next to the `zip` file, then find the `<application name>.exe` file (several levels deeper into the folder tree) and run the file.

The `zip` file contains all application files. It can be shipped to end users. End users will need to unpack the `zip` file and run the `<application name>.exe` file therein.
The application has an embedded JRE, so it can be executed on a computer without JRE installed.


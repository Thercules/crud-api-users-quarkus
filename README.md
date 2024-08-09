
# CRUD-Solicitação Serviços

This project uses Quarkus, the Supersonic Subatomic Java Framework.

## Installation and Setup

### Back-end

#### IntelliJ Community Edition

1. Download and install [IntelliJ Community Edition](https://www.jetbrains.com/idea/download/).
2. Configure IntelliJ to use JDK 22 by going to **File > Project Structure > Project > Project SDK** and adding JDK 22.
![IntelliJ Screenshot](https://i.ibb.co/pjL8gPw/print-intellij.png)

#### Maven

1. Download Maven from the [official site](https://maven.apache.org/download.cgi).
2. Extract the archive and set the `M2_HOME` environment variable to the extracted directory.
3. Add `M2_HOME/bin` to your system `PATH`.

#### JDK 22

1. Download JDK 22 from the [official Oracle website](https://www.oracle.com/java/technologies/javase-jdk22-downloads.html) or use [OpenJDK](https://jdk.java.net/22).
2. Set the `JAVA_HOME` environment variable to the JDK installation directory.
3. Add `JAVA_HOME/bin` to your system `PATH`.

#### MySQL

1. Install MySQL following the instructions on [MySQL's official website](https://dev.mysql.com/doc/mysql-installation-excerpt/).
2. Create a database for the application.
3. Configure your `application.properties` file in Quarkus to connect to your MySQL instance.

#### Docker

1. Install Docker following the instructions on [Docker's official website](https://docs.docker.com/get-docker/).
2. Use Docker to run MySQL and your application containers.
![Docker Screenshot](https://i.ibb.co/3zqZV0L/print-docker.png)

### Front-end

#### Node.js

1. Download and install the latest version of Node.js from the [official website](https://nodejs.org/).
2. Verify the installation by running:
   ```shell
   node -v
   npm -v
   ```

#### VSCode

1. Download and install [Visual Studio Code](https://code.visualstudio.com/).
2. Open your project in VSCode.

#### Vue.js 2

1. Install Vue CLI globally by running:
   ```shell
   npm install -g @vue/cli@4.5.15
   ```
2. Create a new Vue.js 2 project or navigate to your existing project directory and install dependencies:
   ```shell
   npm install
   ```
3. Configure your vue.config.js for the port you want and set up a proxy layer to the port where your API is running to avoid CORS issues:
   ```shell
   const { defineConfig } = require('@vue/cli-service')

	module.exports = defineConfig({
	  transpileDependencies: true,
	  devServer: {
		port: 8084,
		proxy: {
		  '/api': {
			target: 'http://localhost:8080', // Port where your API is running
			changeOrigin: true,
			pathRewrite: { '^/api': '' },
		  },
		},
	  },
	}) 
   ```
4. Run the Vue.js development server on port 8084:
   ```shell
   npm run serve 
   ```

> **_NOTE:_** Ensure your front-end runs on port 8084, while your API runs on port 8080 using Docker to avoid conflicts.

## Project Overview

This project is a modern CRUD API built with SOLID principles using:
- JDK 22
- Quarkus 3.12.3
- Maven 3.9.8
- MySQL
- Docker 4.32.0

It includes endpoints for managing services, users, and service requests.

## Running the Application

### Development Mode

To run the application in development mode with live coding enabled:

```shell
./mvnw compile quarkus:dev
```

> **_NOTE:_** Quarkus now ships with a Dev UI, which is available in dev mode only at [http://localhost:8080/q/dev/](http://localhost:8080/q/dev/).

### Packaging and Running the Application

To package the application:

```shell
./mvnw package
```

This produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory. It’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory. You can run the application using:

```shell
java -jar target/quarkus-app/quarkus-run.jar
```

To build an _über-jar_:

```shell
./mvnw package -Dquarkus.package.jar.type=uber-jar
```

Run the _über-jar_ with:

```shell
java -jar target/*-runner.jar
```

### Creating a Native Executable

To create a native executable:

```shell
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, use a container for the native build:

```shell
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

Run your native executable with:

```shell
./target/thiago-code-1.0.0-SNAPSHOT-runner
```

For more information on native executables, consult [Quarkus Native Guide](https://quarkus.io/guides/maven-tooling).

## API Endpoints

### Services

- **GET /servicos**  
  Retrieve all services.
![VSCode Screenshot](https://i.ibb.co/4P11k88/print-vscode.png)

- **GET /servicos/{id}**  
  Retrieve a specific service by ID.

- **POST /servicos**  
  Create a new service.  
  **Request Body Example:**
  ```json
  {
    "id": 1,
    "name": "Service Name",
    "description": "Service Description"
  }
  ```

- **PUT /servicos**  
  Update an existing service.  
  **Request Body Example:**
  ```json
  {
    "id": 1,
    "name": "Updated Service Name",
    "description": "Updated Description"
  }
  ```

- **DELETE /servicos/{id}**  
  Delete a service by ID.

### Users

- **GET /users**  
  Retrieve all users.

- **GET /users/{id}**  
  Retrieve a specific user by ID.

- **POST /users**  
  Create a new user.  
  **Request Body Example:**
  ```json
  {
    "id": 1,
    "name": "User Name",
    "cpf": "12345678901"
  }
  ```

- **PUT /users**  
  Update an existing user.  
  **Request Body Example:**
  ```json
  {
    "id": 1,
    "name": "Updated User Name",
    "cpf": "09876543210"
  }
  ```

- **DELETE /users/{id}**  
  Delete a user by ID.

### Service Requests

- **POST /solicitacoesServicos**  
  Create a new service request.  
  **Request Body Example:**
  ```json
  {
    "userId": 1,
    "servicoId": 2,
    "status": "PENDING"
  }
  ```

- **GET /solicitacoesServicos**  
  Retrieve all service requests.

- **GET /solicitacoesServicos/{id}**  
  Retrieve a specific service request by ID.

- **PUT /solicitacoesServicos/{id}**  
  Update the status of a service request.  
  **Request Body Example:**
  ```json
  {
    "status": "COMPLETED"
  }
  ```

- **DELETE /solicitacoesServicos/{id}**  
  Delete a service request by ID.
![Insomnia Screenshot](https://i.ibb.co/RH1rmP9/print-insomnia.png)

## Related Guides

- [REST Data with Panache](https://quarkus.io/guides/rest-data-panache): Generate Jakarta REST resources for your Hibernate Panache entities and repositories.
- [RESTEasy Classic's REST Client JSON-B](https://quarkus.io/guides/resteasy-client): JSON-B serialization support for the REST client.
- [RESTEasy Classic](https://quarkus.io/guides/resteasy): REST endpoint framework implementing Jakarta REST and more.
- [JDBC Driver - MySQL](https://quarkus.io/guides/datasource): Connect to the MySQL database via JDBC.
- [Security JPA](https://quarkus.io/guides/security-getting-started): Secure your applications with username/password stored in a database via Jakarta Persistence.

## Frontend Preview

![Frontend Preview](https://i.ibb.co/YQgtK2X/print-front.jpg)

## Author

Thiago-Hercules

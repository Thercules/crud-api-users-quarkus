# Usando uma imagem base com JDK 22
FROM eclipse-temurin:22-jdk-alpine

# Definindo o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copiando o JAR construído para o contêiner
COPY target/thiago-code-1.0.0-SNAPSHOT-runner.jar app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
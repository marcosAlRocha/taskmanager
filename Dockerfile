FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o arquivo JAR gerado pelo Spring Boot
COPY target/*.jar app.jar

# Expõe a porta 8080 (usada pelo Spring Boot)
EXPOSE 8080

# Comando para executar a aplicação
CMD ["java", "-jar", "app.jar"]
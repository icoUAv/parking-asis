# Imagem de execução. O jar é construído e testado antes, no pipeline (job Build).
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY target/estacionamento.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

# Imagen base con Java 21
FROM eclipse-temurin:21-jdk

# Carpeta interna donde se ejecutará la aplicación
WORKDIR /app

# Copia el archivo .jar generado por Maven
COPY target/*.jar app.jar

# Puerto del microservicio de pagos
EXPOSE 8085

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
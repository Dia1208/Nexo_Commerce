# Imagen base con Java 21
FROM eclipse-temurin:21-jdk

# Carpeta interna del contenedor
WORKDIR /app

# Copia el jar generado por Maven
COPY target/*.jar app.jar

# Puerto del microservicio checkout
EXPOSE 8088

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
# Start with a lightweight Java image
FROM openjdk:21

# Set the working directory
WORKDIR /app

# Add the built JAR to the container
ARG JAR_FILE
COPY target/${JAR_FILE} app.jar

# Expose the application's port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
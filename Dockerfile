FROM openjdk:17
COPY target/RBAC-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java", "-jar", "RBAC-0.0.1-SNAPSHOT.jar"]

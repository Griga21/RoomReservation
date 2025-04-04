FROM openjdk:21

WORKDIR /

COPY . .

CMD ["java", "-jar", "reservation-1.0-SNAPSHOT.jar"]
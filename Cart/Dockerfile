FROM openjdk:21
COPY ./target/Cart-0.0.1-SNAPSHOT.jar /usr/src/Cart/
WORKDIR /usr/src/Cart/
EXPOSE 8082
CMD ["java", "-jar", "Cart-0.0.1-SNAPSHOT.jar"]
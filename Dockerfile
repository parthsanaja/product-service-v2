FROM eclipse-temurin:17-jre

WORKDIR /app

COPY ["./product-service-v2-server/target/product-service-v2-server-0.0.1-SNAPSHOT.jar", "."]

EXPOSE 8091

CMD java -jar product-service-v2-server-0.0.1-SNAPSHOT.jar
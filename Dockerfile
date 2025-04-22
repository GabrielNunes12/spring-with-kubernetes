# ────────────────────────────────────────────────
# 1) BUILD & TEST STAGE
#    Uses Maven + Amazon Corretto 21 to compile, test, and package.
# ────────────────────────────────────────────────
FROM maven:3-amazoncorretto-21 AS builder

WORKDIR /app
# copy only what's needed to download deps
COPY pom.xml .
RUN mvn dependency:go-offline -B

# copy sources & run full build (includes tests)
COPY src ./src
RUN mvn clean package -B

# ────────────────────────────────────────────────
# 2) RUNTIME STAGE
#    A minimal Amazon Corretto 21 JRE to run your fat-jar
# ────────────────────────────────────────────────
FROM amazoncorretto:21-alpine

WORKDIR /app
# copy the built jar from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# expose your Spring port
EXPOSE 8080

# run the jar
ENTRYPOINT ["java","-jar","app.jar"]

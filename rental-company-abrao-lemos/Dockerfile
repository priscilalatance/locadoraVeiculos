FROM openjdk:17-alpine

EXPOSE 9090
	
WORKDIR /src

COPY /target/*.jar /src/rental-company-abrao-lemos.jar

ENTRYPOINT ["java", "-jar", "rental-company-abrao-lemos.jar"]


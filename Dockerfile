FROM openjdk:21-jdk

#luu trữ source code
VOLUME /tmp
EXPOSE 8080
WORKDIR /var/app
COPY target/user-0.0.1-SNAPSHOT.jar user-services.jar
#chạy servies với port = 8085
ENTRYPOINT ["java", "-Dserver.port=8085", "-jar", "user-services.jar"]

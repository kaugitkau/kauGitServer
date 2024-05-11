# 베이스 이미지를 지정합니다. 이 예제에서는 OpenJDK 17을 사용합니다.
FROM openjdk:17


# 빌드한 jar 파일을 작업 디렉토리로 복사합니다.
# 이때, 빌드된 jar 파일의 이름을 'app.jar'로 가정합니다. 실제 파일 이름에 맞게 변경해야 할 수 있습니다.
COPY build/libs/*.jar app.jar

# 컨테이너가 시작될 때 실행될 명령어를 지정합니다.
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]
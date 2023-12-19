FROM maven:3.6.3-openjdk-17

RUN mkdir LearningVoyage

WORKDIR LearningVoyage

COPY . .

RUN mvn package -Dmaven.test.skip=true

CMD ["java", "-jar", "target/learningvoyage.war"]
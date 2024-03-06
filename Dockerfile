FROM maven:3.9.6-eclipse-temurin-21

ARG workdir=/usr/local/Lemma4Jolie

COPY install.sh $workdir/install.sh
COPY build.gradle.kts $workdir/build.gradle.kts
COPY lib $workdir/lib
COPY src $workdir/src
COPY gradle $workdir/gradle
COPY gradlew $workdir/gradlew
WORKDIR $workdir

RUN chmod +x install.sh
RUN ./install.sh

ENTRYPOINT ["java", "--add-opens", "java.base/java.lang=ALL-UNNAMED", "-jar", "/usr/local/Lemma4Jolie/build/libs/Lemma4Jolie-1.0-SNAPSHOT-standalone.jar"]
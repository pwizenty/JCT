#!/usr/bin/env bash

do_install_libs() {
    local groupId=$1
    local artifactId=$1
    local version=$2
    local classifier=$3

    if [ -z "$classifier" ]
    then
        local filepath=lib/$artifactId-$version.jar
    else
        local filepath=lib/$artifactId-$version-$classifier.jar
    fi

    mvn install:install-file \
        -Dfile=$filepath \
        -DgroupId=$groupId \
        -DartifactId=$artifactId \
        -Dversion=$version \
        -Dclassifier=$classifier \
        -Dpackaging=jar \
        -DgeneratePom=true
}

do_install_libs "de.fhdo.lemma.data.datadsl" "0.8.6-SNAPSHOT" \
    "assembly"

./gradlew clean
./gradlew standalone
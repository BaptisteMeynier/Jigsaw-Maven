#!/bin/sh

rm -Rf customJre

$JAVA_HOME/bin/jlink \
--module-path \
$JAVA_HOME/jmods:mlib:\
com.java.jigsaw.portfolio.model/target/portfolio-model-1.0-SNAPSHOT.jar:\
com.java.jigsaw.portfolio.spi/target/portfolio-service-spi-1.0-SNAPSHOT.jar:\
com.java.jigsaw.portfolio.api.inmemory/target/inmemory-api-1.0-SNAPSHOT.jar:\
com.java.jigsaw.portfolio.app/target/portfolio-app-1.0-SNAPSHOT.jar \
--add-modules com.java.jigsaw.portfolio.app \
--output customJre \
--launcher launch=com.java.jigsaw.portfolio.app/com.java.jigsaw.portfolio.app.App
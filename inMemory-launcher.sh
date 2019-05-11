#!/bin/sh

$JAVA_HOME/bin/java \
--enable-preview \
--module-path \
com.java.jigsaw.portfolio.model/target/portfolio-model-1.0-SNAPSHOT.jar:\
com.java.jigsaw.portfolio.spi/target/portfolio-service-spi-1.0-SNAPSHOT.jar:\
com.java.jigsaw.portfolio.api.inmemory/target/inmemory-api-1.0-SNAPSHOT.jar:\
com.java.jigsaw.portfolio.app/target/portfolio-app-1.0-SNAPSHOT.jar \
-m \
com.java.jigsaw.portfolio.app/com.java.jigsaw.portfolio.app.App
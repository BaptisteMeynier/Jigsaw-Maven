#!/bin/sh
mkdir -p target/org.mariadb.jdbc
curl http://central.maven.org/maven2/org/mariadb/jdbc/mariadb-java-client/2.4.1/mariadb-java-client-2.4.1.jar --output target/org.mariadb.jdbc/mariadb-java-client-2.4.1.jar

$JAVA_HOME/bin/jlink \
--module-path \
$JAVA_HOME/jmods:mlib:\
target/org.mariadb.jdbc/mariadb-java-client-2.4.1.jar:\
com.java.jigsaw.portfolio.model/target/portfolio-model-1.0-SNAPSHOT.jar:\
com.java.jigsaw.portfolio.spi/target/portfolio-service-spi-1.0-SNAPSHOT.jar:\
com.java.jigsaw.portfolio.api.database/target/database-api-1.0-SNAPSHOT.jar:\
com.java.jigsaw.portfolio.app/target/portfolio-app-1.0-SNAPSHOT.jar \
--add-modules com.java.jigsaw.portfolio.app \
--output customJre
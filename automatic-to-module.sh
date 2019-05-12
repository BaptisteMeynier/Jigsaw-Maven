

mkdir -p tmp/org.apache.commons/

curl http://central.maven.org/maven2/org/apache/commons/commons-lang3/3.4/commons-lang3-3.4.jar --output tmp/org.apache.commons/commons-lang3-3.4.jar

$JAVA_HOME/bin/jdeps --generate-module-info tmp tmp/org.apache.commons/commons-lang3-3.4.jar


$JAVA_HOME/bin/jar --update --file tmp/org.apache.commons/commons-lang3-3.4.jar --module-version=3.3.4-module -C tmp/commons.lang3/ module-info.class

jar --describe-module --file=patched-automatic-modules/commons-lang3-3.4.jar
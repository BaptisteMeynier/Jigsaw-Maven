[baptiste@DESKTOP-FUI7H3K 2.4.1]$ export JAVA_HOME=/home/baptiste/Resources/Jdk/jdk12/latest


[baptiste@DESKTOP-FUI7H3K 2.4.1]$ $JAVA_HOME/bin/jar --describe-module --file=mariadb-java-client-2.4.1.jar
No module descriptor found. Derived automatic module.

org.mariadb.jdbc@2.4.1 automatic
requires java.base mandated
provides java.sql.Driver with org.mariadb.jdbc.Driver
contains org.mariadb.jdbc
contains org.mariadb.jdbc.internal
contains org.mariadb.jdbc.internal.com
contains org.mariadb.jdbc.internal.com.read
contains org.mariadb.jdbc.internal.com.read.dao
contains org.mariadb.jdbc.internal.com.read.resultset
contains org.mariadb.jdbc.internal.com.read.resultset.rowprotocol
contains org.mariadb.jdbc.internal.com.send
contains org.mariadb.jdbc.internal.com.send.authentication
contains org.mariadb.jdbc.internal.com.send.authentication.ed25519
contains org.mariadb.jdbc.internal.com.send.authentication.ed25519.math
contains org.mariadb.jdbc.internal.com.send.authentication.ed25519.math.ed25519
contains org.mariadb.jdbc.internal.com.send.authentication.ed25519.spec
contains org.mariadb.jdbc.internal.com.send.authentication.gssapi
contains org.mariadb.jdbc.internal.com.send.parameters
contains org.mariadb.jdbc.internal.failover
contains org.mariadb.jdbc.internal.failover.impl
contains org.mariadb.jdbc.internal.failover.thread
contains org.mariadb.jdbc.internal.failover.tools
contains org.mariadb.jdbc.internal.io
contains org.mariadb.jdbc.internal.io.input
contains org.mariadb.jdbc.internal.io.output
contains org.mariadb.jdbc.internal.io.socket
contains org.mariadb.jdbc.internal.logging
contains org.mariadb.jdbc.internal.protocol
contains org.mariadb.jdbc.internal.protocol.authentication
contains org.mariadb.jdbc.internal.protocol.tls
contains org.mariadb.jdbc.internal.util
contains org.mariadb.jdbc.internal.util.constant
contains org.mariadb.jdbc.internal.util.dao
contains org.mariadb.jdbc.internal.util.exceptions
contains org.mariadb.jdbc.internal.util.pid
contains org.mariadb.jdbc.internal.util.pool
contains org.mariadb.jdbc.internal.util.scheduler
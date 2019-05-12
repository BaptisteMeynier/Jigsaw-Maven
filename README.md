#Jigsaw Maven
__________________


Studying of Jigsaw [JSR 376](https://openjdk.java.net/projects/jigsaw/spec/) modules and its compatibility with Maven.

##Description
__________________

Implementation of a bit complexe application to practice Jigsaw.
Application is composed of with multiple jigsaw modules and illustrate new service provider interface enhance.
Two implementations are provided in order to get Portfolios, one from database other directly in memory.

##How compile ?
___________________
First, be sure that your JAVA_HOME is set to version >= 9   
example:
```   
export JAVA_HOME=/home/baptiste/Resources/Jdk/jdk12/latest   
``` 
Compile using maven:
```
mvn clean package
```

##How launch ?
___________________

####In memory implementation:
./inMemory-launcher.sh

####Database implementation:
sudo systemctl start mariadb.service  
./database-launcher.sh  

##Jlink:
Jlink [JEP 282](https://openjdk.java.net/jeps/282), assemble and optimize a set of modules and their dependencies into a custom runtime image.
####In memory:
```   
./jlink-inMemory-builder.sh
./jlink-inMemory-runner.sh
```   
####Database:
For the database version I use org.mariadb.jdbc:mariadb-java-client:2.4.1   
I can use the following command to get more information about this jar.
``` 
$JAVA_HOME/bin/jar --describe-module --file=mariadb-java-client-2.4.1.jar
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
``` 
We see that the jar did not provide module-info.xml and is consider like "automatic module" 

If I want to create a custom image with jlink I have to transform this dependency in a real jigsaw module  
In order to get more information about it I can use jdeps
``` 
$JAVA_HOME/bin/jdeps --generate-module-info target/module/org.mariadb.jdbc/mariadb-java-client-2.4.1.jar target/org.mariadb.jdbc/mariadb-java-client-2.4.1.jar
Missing dependence: target/module/org.mariadb.jdbc/mariadb-java-client-2.4.1.jar/org.mariadb.jdbc/module-info.java not generated
Error: missing dependencies
org.mariadb.jdbc
   org.mariadb.jdbc.internal.com.send.authentication.gssapi.GssUtility -> com.sun.jna.Platform                               not found
   org.mariadb.jdbc.internal.com.send.authentication.gssapi.WindowsNativeSspiAuthentication -> com.sun.jna.platform.win32.Sspi                    not found
   org.mariadb.jdbc.internal.com.send.authentication.gssapi.WindowsNativeSspiAuthentication -> com.sun.jna.platform.win32.Sspi$CtxtHandle         not found
   org.mariadb.jdbc.internal.com.send.authentication.gssapi.WindowsNativeSspiAuthentication -> com.sun.jna.platform.win32.Sspi$SecBufferDesc      not found
   org.mariadb.jdbc.internal.com.send.authentication.gssapi.WindowsNativeSspiAuthentication -> waffle.windows.auth.IWindowsSecurityContext        not found
   org.mariadb.jdbc.internal.com.send.authentication.gssapi.WindowsNativeSspiAuthentication -> waffle.windows.auth.impl.WindowsSecurityContextImpl not found
   org.mariadb.jdbc.internal.io.socket.NamedPipeSocket -> com.sun.jna.platform.win32.Kernel32                not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.FunctionMapper                         not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.LastErrorException                     not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.Library                                not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.Platform                               not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.Pointer                                not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.TypeMapper                             not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.platform.win32.BaseTSD                 not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.platform.win32.BaseTSD$SIZE_T          not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.platform.win32.WinNT                   not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.platform.win32.WinNT$HANDLE            not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.ptr.IntByReference                     not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.ptr.PointerByReference                 not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.win32.W32APIFunctionMapper             not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.win32.W32APITypeMapper                 not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Advapi32 -> com.sun.jna.Native                                 not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Advapi32 -> com.sun.jna.ptr.IntByReference                     not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Advapi32 -> com.sun.jna.ptr.PointerByReference                 not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Advapi32 -> com.sun.jna.win32.StdCallLibrary                   not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Advapi32$SECURITY_ATTRIBUTES -> com.sun.jna.Pointer                                not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Advapi32$SECURITY_ATTRIBUTES -> com.sun.jna.Structure                              not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Kernel32 -> com.sun.jna.LastErrorException                     not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Kernel32 -> com.sun.jna.Native                                 not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Kernel32 -> com.sun.jna.Pointer                                not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Kernel32 -> com.sun.jna.platform.win32.BaseTSD                 not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Kernel32 -> com.sun.jna.platform.win32.BaseTSD$SIZE_T          not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Kernel32 -> com.sun.jna.platform.win32.WinNT                   not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Kernel32 -> com.sun.jna.platform.win32.WinNT$HANDLE            not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Kernel32 -> com.sun.jna.win32.StdCallLibrary                   not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$SharedMemoryInputStream -> com.sun.jna.Pointer                                not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$SharedMemoryInputStream -> com.sun.jna.platform.win32.WinNT                   not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$SharedMemoryInputStream -> com.sun.jna.platform.win32.WinNT$HANDLE            not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$SharedMemoryOutputStream -> com.sun.jna.Pointer                                not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$SharedMemoryOutputStream -> com.sun.jna.platform.win32.WinNT                   not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$SharedMemoryOutputStream -> com.sun.jna.platform.win32.WinNT$HANDLE            not found
   org.mariadb.jdbc.internal.io.socket.SocketUtility  -> com.sun.jna.Platform                               not found
   org.mariadb.jdbc.internal.io.socket.UnixDomainSocket -> com.sun.jna.LastErrorException                     not found
   org.mariadb.jdbc.internal.io.socket.UnixDomainSocket -> com.sun.jna.Native                                 not found
   org.mariadb.jdbc.internal.io.socket.UnixDomainSocket -> com.sun.jna.Platform                               not found
   org.mariadb.jdbc.internal.io.socket.UnixDomainSocket$SockAddr -> com.sun.jna.Structure                              not found
   org.mariadb.jdbc.internal.io.socket.UnixDomainSocket$UnixSocketInputStream -> com.sun.jna.LastErrorException                     not found
   org.mariadb.jdbc.internal.io.socket.UnixDomainSocket$UnixSocketOutputStream -> com.sun.jna.LastErrorException                     not found
   org.mariadb.jdbc.internal.logging.LoggerFactory    -> org.slf4j.Logger                                   not found
   org.mariadb.jdbc.internal.logging.LoggerFactory    -> org.slf4j.LoggerFactory                            not found
   org.mariadb.jdbc.internal.logging.Slf4JLogger      -> org.slf4j.Logger                                   not found
   org.mariadb.jdbc.internal.util.pid.JnaPidFactory   -> com.sun.jna.Platform                               not found
   org.mariadb.jdbc.internal.util.pid.JnaPidFactory   -> com.sun.jna.platform.win32.Kernel32                not found
   org.mariadb.jdbc.internal.util.pid.JnaPidFactory$CLibrary -> com.sun.jna.Library                                not found
   org.mariadb.jdbc.internal.util.pid.JnaPidFactory$CLibrary -> com.sun.jna.Native       
```
There is too much dependency to convert in jigsaw I decide to wait a jigsaw version provide by oracle

Trying to launch jlink creation:
```./jlink-database-builder.sh 
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100  575k  100  575k    0     0  2222k      0 --:--:-- --:--:-- --:--:-- 2222k
Error: automatic module cannot be used with jlink: org.mariadb.jdbc from file:///home/baptiste/IdeaProjects/jigsaw-maven/target/org.mariadb.jdbc/mariadb-java-client-2.4.1.jar
```

I should convert each dependencies to jigsaw module   

```
$JAVA_HOME/bin/jdeps --generate-module-info tmp target/org.mariadb.jdbc/mariadb-java-client-2.4.1.jar
Missing dependence: tmp/org.mariadb.jdbc/module-info.java not generated
Error: missing dependencies
org.mariadb.jdbc
   org.mariadb.jdbc.internal.com.send.authentication.gssapi.GssUtility -> com.sun.jna.Platform                               not found
   org.mariadb.jdbc.internal.com.send.authentication.gssapi.WindowsNativeSspiAuthentication -> com.sun.jna.platform.win32.Sspi                    not found
   org.mariadb.jdbc.internal.com.send.authentication.gssapi.WindowsNativeSspiAuthentication -> com.sun.jna.platform.win32.Sspi$CtxtHandle         not found
   org.mariadb.jdbc.internal.com.send.authentication.gssapi.WindowsNativeSspiAuthentication -> com.sun.jna.platform.win32.Sspi$SecBufferDesc      not found
   org.mariadb.jdbc.internal.com.send.authentication.gssapi.WindowsNativeSspiAuthentication -> waffle.windows.auth.IWindowsSecurityContext        not found
   org.mariadb.jdbc.internal.com.send.authentication.gssapi.WindowsNativeSspiAuthentication -> waffle.windows.auth.impl.WindowsSecurityContextImpl not found
   org.mariadb.jdbc.internal.io.socket.NamedPipeSocket -> com.sun.jna.platform.win32.Kernel32                not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.FunctionMapper                         not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.LastErrorException                     not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.Library                                not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.Platform                               not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.Pointer                                not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.TypeMapper                             not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.platform.win32.BaseTSD                 not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.platform.win32.BaseTSD$SIZE_T          not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.platform.win32.WinNT                   not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.platform.win32.WinNT$HANDLE            not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.ptr.IntByReference                     not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.ptr.PointerByReference                 not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.win32.W32APIFunctionMapper             not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket -> com.sun.jna.win32.W32APITypeMapper                 not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Advapi32 -> com.sun.jna.Native                                 not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Advapi32 -> com.sun.jna.ptr.IntByReference                     not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Advapi32 -> com.sun.jna.ptr.PointerByReference                 not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Advapi32 -> com.sun.jna.win32.StdCallLibrary                   not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Advapi32$SECURITY_ATTRIBUTES -> com.sun.jna.Pointer                                not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Advapi32$SECURITY_ATTRIBUTES -> com.sun.jna.Structure                              not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Kernel32 -> com.sun.jna.LastErrorException                     not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Kernel32 -> com.sun.jna.Native                                 not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Kernel32 -> com.sun.jna.Pointer                                not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Kernel32 -> com.sun.jna.platform.win32.BaseTSD                 not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Kernel32 -> com.sun.jna.platform.win32.BaseTSD$SIZE_T          not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Kernel32 -> com.sun.jna.platform.win32.WinNT                   not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Kernel32 -> com.sun.jna.platform.win32.WinNT$HANDLE            not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$Kernel32 -> com.sun.jna.win32.StdCallLibrary                   not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$SharedMemoryInputStream -> com.sun.jna.Pointer                                not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$SharedMemoryInputStream -> com.sun.jna.platform.win32.WinNT                   not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$SharedMemoryInputStream -> com.sun.jna.platform.win32.WinNT$HANDLE            not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$SharedMemoryOutputStream -> com.sun.jna.Pointer                                not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$SharedMemoryOutputStream -> com.sun.jna.platform.win32.WinNT                   not found
   org.mariadb.jdbc.internal.io.socket.SharedMemorySocket$SharedMemoryOutputStream -> com.sun.jna.platform.win32.WinNT$HANDLE            not found
   org.mariadb.jdbc.internal.io.socket.SocketUtility  -> com.sun.jna.Platform                               not found
   org.mariadb.jdbc.internal.io.socket.UnixDomainSocket -> com.sun.jna.LastErrorException                     not found
   org.mariadb.jdbc.internal.io.socket.UnixDomainSocket -> com.sun.jna.Native                                 not found
   org.mariadb.jdbc.internal.io.socket.UnixDomainSocket -> com.sun.jna.Platform                               not found
   org.mariadb.jdbc.internal.io.socket.UnixDomainSocket$SockAddr -> com.sun.jna.Structure                              not found
   org.mariadb.jdbc.internal.io.socket.UnixDomainSocket$UnixSocketInputStream -> com.sun.jna.LastErrorException                     not found
   org.mariadb.jdbc.internal.io.socket.UnixDomainSocket$UnixSocketOutputStream -> com.sun.jna.LastErrorException                     not found
   org.mariadb.jdbc.internal.logging.LoggerFactory    -> org.slf4j.Logger                                   not found
   org.mariadb.jdbc.internal.logging.LoggerFactory    -> org.slf4j.LoggerFactory                            not found
   org.mariadb.jdbc.internal.logging.Slf4JLogger      -> org.slf4j.Logger                                   not found
   org.mariadb.jdbc.internal.util.pid.JnaPidFactory   -> com.sun.jna.Platform                               not found
   org.mariadb.jdbc.internal.util.pid.JnaPidFactory   -> com.sun.jna.platform.win32.Kernel32                not found
   org.mariadb.jdbc.internal.util.pid.JnaPidFactory$CLibrary -> com.sun.jna.Library                                not found
   org.mariadb.jdbc.internal.util.pid.JnaPidFactory$CLibrary -> com.sun.jna.Native                                 not found
```

####Other information:
For your old jar you can add an entry in the manifest to define the automatic module name "Automatic-Module-Name:MyModuleName"

###Documentations
https://blog.soat.fr/2017/05/java-9-la-revolution-des-modules/
https://examples.javacodegeeks.com/core-java/java-9-jigsaw-project-tutorial/
https://openjdk.java.net/projects/jigsaw/spec/sotms/
###todo
__________________
see how illustrate jigsaw open feature   
see if jlink maven plugin is depreciated



Notes:
___________________________
mvn -version
Apache Maven 3.6.0 (97c98ec64a1fdfee7767ce5ffb20918da4f719f3; 2018-10-24T20:41:47+02:00)
Maven home: /home/baptiste/Resources/Maven/latest
Java version: 12.0.1, vendor: Oracle Corporation, runtime: /home/baptiste/Resources/Jdk/jdk12/jdk-12.0.1



[baptiste@DESKTOP-FUI7H3K jigsaw-maven]$                           not found

[baptiste@DESKTOP-FUI7H3K 2.4.1]$ export JAVA_HOME=/home/baptiste/Resources/Jdk/jdk12/latest
[baptiste@DESKTOP-FUI7H3K 2.4.1]$ sudo systemctl start mariadb.service

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


[baptiste@DESKTOP-FUI7H3K jigsaw-maven]$ $JAVA_HOME/bin/jdeps --generate-module-info target/module/org.mariadb.jdbc/mariadb-java-client-2.4.1.jar target/org.mariadb.jdbc/mariadb-java-client-2.4.1.jar
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
   org.mariadb.jdbc.internal.util.pid.JnaPidFactory$CLibrary -> com.sun.jna.Native                                 not found

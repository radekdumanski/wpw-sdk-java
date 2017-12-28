# Prerequisities:
1. Download and install Google Chrome in default location under C:\Program Files (x86).
2. Download and add to PATH environment variable Apache Maven https://maven.apache.org/download.cgi
3. Download and install GIT https://git-scm.com/downloads
4. Download and install JAVA 8 SDK http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
5. Set JAVA_HOME environment variable pointing to JDK 8.
# Setup:
1. cd %userprofile%
2. git clone https://github.com/WPTechInnovation/wpw-sdk-java.git
3. cd wpw-sdk-java
4. git submodule init
5. git submodule update
6. mvn
7. cd car-example
8. java -jar target\car-example-v0.10-alpha.jar
# Additional information:
* To configure the use of offline/online transaction server, set **api_endpoint** to one of:

  offline - http://192.168.0.1:8080/v1 - in this case we need to configure ip and port of offline server;  
  online - https://api.worldpay.com/v1.  
* there is an autostart batch script for Intel Compute Stick inside the repository, simply copy it to Windows autostart folder.


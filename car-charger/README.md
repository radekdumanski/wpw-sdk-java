# Prerequisities:
1. Verify that SW Guard Extensions (SGX) is enabled in BIOS and enable if not.
2. Windows Settings: Turn off screen timeout settings in "Power & sleep" to "Never" and "Screen Server Settings" to "None".
3. Download and install Google Chrome in default location under C:\Program Files (x86).
4. Download and add to PATH environment variable Apache Maven https://maven.apache.org/download.cgi
5. Download and install GIT https://git-scm.com/downloads
6. Download and install JAVA 8 SDK http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
7. Download and install SGX SDK https://software.intel.com/en-us/sgx-sdk
8. Download and install SGX PSW (same link, optional choice during download)
9. Download and install Windows 10 SDK https://developer.microsoft.com/pl-pl/windows/downloads/windows-10-sdk
10. Download and install Visual Studio Community https://www.visualstudio.com/pl/vs/community/
11. Set JAVA_HOME environment variable pointing to JDK 8.
# Setup:
1. cd %userprofile%
2. git clone https://github.com/WPTechInnovation/wpw-sdk-java.git
3. cd wpw-sdk-java
3. git checkout develop
4. git submodule init
5. git submodule update
6. mvn
7. cd car-charger
8. java -jar target\car-charger-v0.10-alpha.jar
# Additional information:
* To configure the use of offline/online transaction server, set **api_endpoint** to one of:

  offline - http://192.168.0.1:8080/v1 - in this case we need to configure ip and port of offline server;  
  online - https://api.worldpay.com/v1.  
* There is an autostart batch script for Intel Compute Stick inside the repository (%userprofile%\wpw-sdk-java\car-charger\charger.bat), simply copy it to Windows autostart folder (C:\Users\charger\AppData\Roaming\Microsoft\Windows\Start Menu\Programs\Startup)

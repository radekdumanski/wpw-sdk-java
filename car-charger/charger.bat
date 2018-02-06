cd %userprofile%\wpw-sdk-java\car-charger
start java -jar target\car-charger-v0.12-alpha.jar &
"C:\Program Files (x86)\Google\Chrome\Application\chrome.exe" -kiosk -incognito -fullscreen "http://127.0.0.1:8008/"

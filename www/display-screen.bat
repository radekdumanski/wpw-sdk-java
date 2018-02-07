@echo off
cls

cd %userprofile%\wpw-sdk-go\applications\offline-server
START /MIN "OWP" offline-server.exe &

cd %userprofile%
"%programfiles(x86)%\Google\Chrome\Application\chrome.exe" --kiosk --app-auto-launched --start-fullscreen localhost

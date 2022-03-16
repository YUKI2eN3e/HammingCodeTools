@ECHO OFF
java -jar HammingCodeTools.jar
echo %cmdcmdline%|find /i """%~f0""">nul && pause
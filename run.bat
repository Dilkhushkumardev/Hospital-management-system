@echo off
title Hospital Management System Launcher
echo Starting Hospital Management System...
start javaw -cp "bin;mysql-connector-java-8.0.28.jar;ResultSet2xml.jar" hospital.management.system.Login
exit

@echo off
echo Очистка проекта...
call mvn clean
rmdir /s /q target
rmdir /s /q allure-report
echo Проект очищен!
pause
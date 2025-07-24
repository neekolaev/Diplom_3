@echo off
echo Запуск тестов только в Chrome...
call mvn clean test -Dbrowser=chrome
call allure serve target/allure-results
pause
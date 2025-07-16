@echo off
echo Запуск тестов только в Yandex Browser...
call mvn clean test -Dbrowser=yandex
call allure serve target/allure-results
pause
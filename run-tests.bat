@echo off
echo ========================================
echo    STELLAR BURGERS AUTOMATION TESTS
echo ========================================

echo.
echo [1/4] Запуск тестов в Chrome...
echo ========================================
call mvn clean test -Dbrowser=chrome
if errorlevel 1 (
    echo ОШИБКА: Тесты в Chrome завершились с ошибкой!
    pause
    exit /b 1
)

echo.
echo [2/4] Запуск тестов в Yandex Browser...
echo ========================================
call mvn clean test -Dbrowser=yandex
if errorlevel 1 (
    echo ОШИБКА: Тесты в Yandex Browser завершились с ошибкой!
    pause
    exit /b 1
)

echo.
echo [3/4] Генерация Allure отчета...
echo ========================================
call allure generate target/allure-results --clean -o target/allure-report
if errorlevel 1 (
    echo ОШИБКА: Не удалось сгенерировать Allure отчет!
    pause
    exit /b 1
)

echo.
echo [4/4] Открытие Allure отчета...
echo ========================================
call allure open target/allure-report

echo.
echo ========================================
echo    ТЕСТЫ ЗАВЕРШЕНЫ УСПЕШНО!
echo ========================================
pause
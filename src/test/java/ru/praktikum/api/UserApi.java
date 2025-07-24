package ru.praktikum.api;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import ru.praktikum.models.User;
import ru.praktikum.models.UserCredentials;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api";

    static {
        RestAssured.baseURI = BASE_URL;
    }

    @Step("Создание пользователя через API")
    public static Response createUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post("/auth/register");
    }

    @Step("Авторизация пользователя через API")
    public static Response loginUser(UserCredentials credentials) {
        return given()
                .header("Content-Type", "application/json")
                .body(credentials)
                .when()
                .post("/auth/login");
    }

    @Step("Удаление пользователя через API")
    public static Response deleteUser(String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .when()
                .delete("/auth/user");
    }
}
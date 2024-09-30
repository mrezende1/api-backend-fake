package org.example.service;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ApiService {

    private static final String BASE_URL = "https://reqres.in/api";

    public Response getUsersPage(int page) {
        return given()
                .queryParam("page", page)
                .when()
                .get(BASE_URL + "/users");
    }


    public Response getUsersPageInvalid(int page) {
        return given()
                .when()
                .get(BASE_URL + "/users/" + page);
    }

    public Response getUserInvalidPage(int page) {
        return given()
                .when()
                .get(BASE_URL + "/users/" + page);
    }

    public Response createUser(String name, String job) {
        String requestBody = String.format("{\"name\": \"%s\", \"job\": \"%s\"}", name, job);
        return given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post(BASE_URL + "/users");
    }

    public Response updateUser(int id, String name, String job) {
        String requestBody = String.format("{\"name\": \"%s\", \"job\": \"%s\"}", name, job);
        return given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .put(BASE_URL + "/users/" + id);
    }

    public Response patchUser(int id, String name, String job) {
        String requestBody = String.format("{\"name\": \"%s\", \"job\": \"%s\"}", name, job);
        return given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .patch(BASE_URL + "/users/" + id);
    }

    public Response deleteUser(int id) {
        return given()
                .when()
                .delete(BASE_URL + "/users/" + id);
    }
}

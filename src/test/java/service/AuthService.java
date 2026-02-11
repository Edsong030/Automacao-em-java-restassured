package service;

import io.restassured.response.Response;
import model.Usuario;

import static io.restassured.RestAssured.given;

public class AuthService {

    public String gerarToken() {

        // cria usuário admin
        String email = "admin" + System.currentTimeMillis() + "@qa.com";

        Usuario admin = new Usuario(
                "Admin",
                email,
                "123456",
                "true"
        );

        given()
                .header("Content-Type", "application/json")
                .body(admin)
                .when()
                .post("/usuarios");

        // faz login
        String body = "{\n" +
                "\"email\": \"" + email + "\",\n" +
                "\"password\": \"123456\"\n" +
                "}";

        Response response = given()
                .header("Content-Type", "application/json")
                .body(body)
                .when()
                .post("/login");

        return response.jsonPath().getString("authorization");
    }
}

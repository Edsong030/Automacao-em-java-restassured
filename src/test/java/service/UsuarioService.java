package service;

import io.restassured.response.Response;
import model.Usuario;

import static io.restassured.RestAssured.given;

public class UsuarioService {

    AuthService authService = new AuthService();

    public Response listarUsuarios() {
        return given()
                .when()
                .get("/usuarios");
    }

    public Response cadastrarUsuario(Usuario usuario) {
        String token = authService.gerarToken();

        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(usuario)
                .when()
                .post("/usuarios");
    }

    public Response editarUsuario(String id, Usuario usuario) {
        String token = authService.gerarToken();

        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .body(usuario)
                .when()
                .put("/usuarios/" + id);
    }

    public Response deletarUsuario(String id) {
        String token = authService.gerarToken();

        return given()
                .header("Authorization", token)
                .when()
                .delete("/usuarios/" + id);
    }
}

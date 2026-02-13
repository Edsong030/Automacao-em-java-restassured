package service;

import config.BaseTest;
import io.restassured.response.Response;
import model.Usuario;

import static io.restassured.RestAssured.given;

public class UsuarioService extends BaseTest {

    public Response listarUsuarios() {
        return given()
                .spec(requestSpecification)
                .when()
                .get("/usuarios");
    }

    public Response cadastrarUsuario(Usuario usuario) {
        return given()
                .spec(requestSpecification)
                .body(usuario)
                .when()
                .post("/usuarios");
    }

    public Response editarUsuario(String id, Usuario usuario) {
        return given()
                .spec(requestSpecification)
                .body(usuario)
                .when()
                .put("/usuarios/" + id);
    }

    public Response deletarUsuario(String id) {
        return given()
                .spec(requestSpecification)
                .when()
                .delete("/usuarios/" + id);
    }

    public Response buscarUsuarioPorId(String id) {
        return given()
                .spec(requestSpecification)
                .when()
                .get("/usuarios/" + id);
    }
}

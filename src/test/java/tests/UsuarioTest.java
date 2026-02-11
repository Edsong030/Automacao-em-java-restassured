package tests;

import config.BaseTest;
import io.restassured.response.Response;
import model.Usuario;
import org.junit.jupiter.api.Test;
import service.UsuarioService;

import static org.hamcrest.Matchers.*;

public class UsuarioTest extends BaseTest {

    UsuarioService usuarioService = new UsuarioService();

    @Test
    public void deveListarUsuarios() {
        usuarioService.listarUsuarios()
                .then()
                .statusCode(200)
                .body("usuarios", notNullValue());
    }

    @Test
    public void deveCadastrarUsuario() {
        Usuario usuario = new Usuario(
                "Fulano",
                "fulano" + System.currentTimeMillis() + "@qa.com",
                "123456",
                "true"
        );

        usuarioService.cadastrarUsuario(usuario)
                .then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"));
    }

    @Test
    public void deveEditarUsuario() {
        // 1. Criar usuário
        Usuario usuario = new Usuario(
                "Fulano",
                "fulano" + System.currentTimeMillis() + "@qa.com",
                "123456",
                "true"
        );

        Response resposta = usuarioService.cadastrarUsuario(usuario);
        String id = resposta.jsonPath().getString("_id");

        // 2. Editar usuário
        Usuario usuarioAtualizado = new Usuario(
                "Fulano Editado",
                "editado" + System.currentTimeMillis() + "@qa.com",
                "123456",
                "true"
        );

        usuarioService.editarUsuario(id, usuarioAtualizado)
                .then()
                .statusCode(200)
                .body("message", equalTo("Registro alterado com sucesso"));
    }

    @Test
    public void deveDeletarUsuario() {
        // 1. Criar usuário
        Usuario usuario = new Usuario(
                "Fulano",
                "fulano" + System.currentTimeMillis() + "@qa.com",
                "123456",
                "true"
        );

        Response resposta = usuarioService.cadastrarUsuario(usuario);
        String id = resposta.jsonPath().getString("_id");

        // 2. Deletar usuário
        usuarioService.deletarUsuario(id)
                .then()
                .statusCode(200)
                .body("message", equalTo("Registro excluído com sucesso"));
    }
}

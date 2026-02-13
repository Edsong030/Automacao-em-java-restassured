package tests;

import config.BaseTest;
import io.restassured.response.Response;
import model.Usuario;
import org.junit.jupiter.api.Test;
import service.UsuarioService;

import static org.hamcrest.Matchers.*;

public class UsuarioTest extends BaseTest {

    UsuarioService usuarioService = new UsuarioService();

    private String gerarEmailUnico() {
        return "usuario" + System.currentTimeMillis() + "@qa.com";
    }

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
                gerarEmailUnico(),
                "123456",
                "true"
        );

        usuarioService.cadastrarUsuario(usuario)
                .then()
                .statusCode(201)
                .body("message", equalTo("Cadastro realizado com sucesso"))
                .body("_id", notNullValue());
    }

    @Test
    public void naoDeveCadastrarUsuarioDuplicado() {
        String email = gerarEmailUnico();

        Usuario usuario = new Usuario(
                "Fulano",
                email,
                "123456",
                "true"
        );

        // Primeiro cadastro
        usuarioService.cadastrarUsuario(usuario)
                .then()
                .statusCode(201);

        // Segundo cadastro com mesmo email
        usuarioService.cadastrarUsuario(usuario)
                .then()
                .statusCode(400)
                .body("message", equalTo("Este email já está sendo usado"));
    }

    @Test
    public void deveEditarUsuario() {
        // 1. Criar usuário
        Usuario usuario = new Usuario(
                "Fulano",
                gerarEmailUnico(),
                "123456",
                "true"
        );

        Response resposta = usuarioService.cadastrarUsuario(usuario);
        String id = resposta.jsonPath().getString("_id");

        // 2. Editar usuário
        Usuario usuarioAtualizado = new Usuario(
                "Fulano Editado",
                gerarEmailUnico(),
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
                gerarEmailUnico(),
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

    // =========================
    // Testes negativos
    // =========================

    @Test
    public void naoDeveCadastrarUsuarioSemNome() {
        Usuario usuario = new Usuario(
                "",
                gerarEmailUnico(),
                "123456",
                "true"
        );

        usuarioService.cadastrarUsuario(usuario)
                .then()
                .statusCode(400);
    }

    @Test
    public void naoDeveCadastrarUsuarioSemEmail() {
        Usuario usuario = new Usuario(
                "Fulano",
                "",
                "123456",
                "true"
        );

        usuarioService.cadastrarUsuario(usuario)
                .then()
                .statusCode(400);
    }

    @Test
    public void naoDeveCadastrarUsuarioComEmailInvalido() {
        Usuario usuario = new Usuario(
                "Fulano",
                "email_invalido",
                "123456",
                "true"
        );

        usuarioService.cadastrarUsuario(usuario)
                .then()
                .statusCode(400);
    }
}

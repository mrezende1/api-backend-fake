package org.example.stepDefinitions;

import io.restassured.response.Response;
import org.example.service.ApiService;
import org.example.model.CreateUserResponse;
import org.example.setup.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.hamcrest.Matchers.*;

public class UserApiSteps extends TestBase {
    private Response response;
    private CreateUserResponse userResponse;
    private final ApiService apiService = new ApiService();

    @Given("que eu solicito a lista de usuários na página {int}")
    public void que_eu_solicito_a_lista_de_usuarios_na_pagina(int page) {
        response = apiService.getUsersPage(page);
    }

    @When("eu faço a requisição")
    public void eu_faco_a_requisicao() {
        // A requisição já foi feita no passo anterior
    }

    @Then("o status da resposta deve ser {int}")
    public void o_status_da_resposta_deve_ser(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("a página deve ser {int}")
    public void a_pagina_deve_ser(int page) {
        response.then().body("page", equalTo(page));
    }

    @Then("a lista de usuários deve ter {int} usuários")
    public void a_lista_de_usuarios_deve_ter_usuarios(int size) {
        response.then().body("data", hasSize(size));
    }

    @Then("o primeiro usuário deve ter o email {string}")
    public void o_primeiro_usuario_deve_ter_o_email(String email) {
        response.then().body("data[0].email", equalTo(email));
    }

    @Given("que eu criar um usuário com nome {string} e trabalho {string}")
    public void que_eu_criar_um_usuario_com_nome_e_trabalho(String name, String job) {
        response = apiService.createUser(name, job);
        userResponse = response.as(CreateUserResponse.class);
    }

    @Then("o usuário criado deve ter nome {string} e trabalho {string}")
    public void o_usuario_criado_deve_ter_nome_e_trabalho(String name, String job) {
        response.then().statusCode(201);
        assert userResponse.getName().equals(name);
        assert userResponse.getJob().equals(job);
    }

    @Given("que eu atualizar o usuário com ID {int} para ter nome {string} e trabalho {string}")
    public void que_eu_atualizar_o_usuario_com_id_para_ter_nome_e_trabalho(int id, String name, String job) {
        response = apiService.updateUser(id, name, job);
    }

    @Then("o usuário atualizado deve ter nome {string} e trabalho {string}")
    public void o_usuario_atualizado_deve_ter_nome_e_trabalho(String name, String job) {
        response.then()
                .statusCode(200)
                .body("name", equalTo(name))
                .body("job", equalTo(job));
    }

    @Given("que eu deletar o usuário com ID {int}")
    public void que_eu_deletar_o_usuario_com_id(int id) {
        response = apiService.deleteUser(id);
    }

    @Then("o corpo da resposta deve ser igual a {string}")
    public void o_corpo_da_resposta_deve_ser_igual_a(String body) {
        response.then().body(equalTo(body));
    }

    @Given("que eu solicito uma página inválida de usuários na página {int}")
    public void que_eu_solicito_uma_pagina_invalida_de_usuarios_na_pagina(int page) {
        response = apiService.getUserInvalidPage(page);
    }

    @Then("o corpo da resposta deve ser vazio")
    public void o_corpo_da_resposta_deve_ser_vazio() {
        response.then().body(equalTo("{}"));
    }

    @Given("que eu solicito a lista de usuários na página invalida {int}")
    public void queEuSolicitoAListaDeUsuáriosNaPáginaInvalida(int page) {
        response = apiService.getUsersPageInvalid(page);
    }
}

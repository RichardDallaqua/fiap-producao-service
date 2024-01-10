package com.fiap.producao.bdd;

import com.fiap.producao.domain.PedidoDomain;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class StepDefinition {

    private Response response;

    private PedidoDomain pedido;
    private final String ENDPOINT_API_PEDIDO = "http://localhost:8080/pedidos/";

    @Quando("solicito solicito o retorno desse pedido utilizando o ID:{string}")
    public void solicitoSolicitoORetornoDessePedidoUtilizandoO(String idPedido) {
        var urlBuscarPedido = ENDPOINT_API_PEDIDO.concat(idPedido.concat("/buscar"));
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get(urlBuscarPedido);
    }
    @Entao("o código de status da resposta deve ser {int}")
    public void o_codigo_de_status_da_resposta_deve_ser(Integer statusCode) {
        response.then()
                .statusCode(statusCode);
    }

    @E("a resposta deve conter o retorno de acordo com o contrato do pedido")
    public void aRespostaDeveConterORetornoDeAcordoComOContratoDoPedido() {
        response.then()
                .body(matchesJsonSchemaInClasspath("schemas/pedido.schema.json"));
    }

    @Quando("atualizo o status do pedido para o ID {string} para {string}")
    public void atualizoOStatusDoPedidoParaOIDPara(String idPedido, String status) {
        var urlBuscarPedido = ENDPOINT_API_PEDIDO.concat(idPedido.concat("/status/".concat(status)));
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put(urlBuscarPedido);
    }
}

package com.fiap.producao.dataprovider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.producao.commons.type.StatusPedido;
import com.fiap.producao.controller.dto.PedidoResponseDTO;
import com.fiap.producao.dataprovider.dto.AtualizarStatusPedidoDTO;
import com.fiap.producao.domain.PedidoDomain;
import com.fiap.producao.services.gateways.PedidoGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Component
@Slf4j
public class PedidoDataProvider implements PedidoGateway {

    private static final String URL_PEDIDO_SERVICE = "https://007z3.wiremockapi.cloud/pedidos/451b7ce3-373e-44a2-8c10-df163540056c/buscar";
    private static final String URL_PEDIDO_SERVICE_STATUS = "https://007z3.wiremockapi.cloud/pedidos/451b7ce3-373e-44a2-8c10-df163540056c/status/CANCELADO";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PedidoDocumentMapper pedidoDocumentMapper;

    public PedidoDomain buscarPedido(UUID idPedido) {

        WebClient webClient = WebClient.create();
        PedidoResponseDTO pedidoResponseDTO = webClient.get().uri(URL_PEDIDO_SERVICE).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(PedidoResponseDTO.class).block();

        return  pedidoDocumentMapper.toDomain(pedidoResponseDTO);
    }

    public PedidoDomain atualizarPedido(UUID idPedido, StatusPedido statusPedido) {

        WebClient webClient = WebClient.create();
        PedidoResponseDTO pedidoResponseDTO =  webClient.put()
                .uri(URL_PEDIDO_SERVICE_STATUS)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PedidoResponseDTO.class)
                .block();

        return  pedidoDocumentMapper.toDomain(pedidoResponseDTO);
    }
}

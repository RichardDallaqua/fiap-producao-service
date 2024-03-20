package com.fiap.producao.dataprovider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.producao.commons.type.StatusPedido;
import com.fiap.producao.controller.dto.PedidoResponseDTO;
import com.fiap.producao.dataprovider.dto.AtualizarStatusPedidoDTO;
import com.fiap.producao.domain.PedidoDomain;
import com.fiap.producao.services.gateways.PedidoGateway;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.UUID;

@Component
@Slf4j
public class PedidoDataProvider implements PedidoGateway {

    @Value("${gateways.pedidos.url}")
    private static String URL_PEDIDO_SERVICE;

    @Value("${gateways.pedidos.busca}")
    private static String URL_PEDIDO_SERVICE_BUSCA;
    @Value("${gateways.pedidos.status}")
    private static String URL_PEDIDO_SERVICE_STATUS;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PedidoDocumentMapper pedidoDocumentMapper;

    public PedidoDomain buscarPedido(UUID idPedido) {

        WebClient webClient = WebClient.create();
        PedidoResponseDTO pedidoResponseDTO = webClient.get().uri(URL_PEDIDO_SERVICE.concat(URL_PEDIDO_SERVICE_BUSCA)).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(PedidoResponseDTO.class).block();

        return  pedidoDocumentMapper.toDomain(pedidoResponseDTO);
    }

    public PedidoDomain atualizarPedido(UUID idPedido, StatusPedido statusPedido) {

        WebClient webClient = WebClient.create();
        PedidoResponseDTO pedidoResponseDTO =  webClient.put()
                .uri(URL_PEDIDO_SERVICE.concat(URL_PEDIDO_SERVICE_STATUS))
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PedidoResponseDTO.class)
                .block();

        return  pedidoDocumentMapper.toDomain(pedidoResponseDTO);
    }
}

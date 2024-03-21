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

    @Value("${external.gateways.url}")
    private String url;

    @Value("${external.gateways.endpoint}")
    private String endpoint;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PedidoDocumentMapper pedidoDocumentMapper;

    public PedidoDomain buscarPedido(UUID idPedido) {

        WebClient webClient = WebClient.create();
        PedidoResponseDTO pedidoResponseDTO = webClient.get().uri(url.concat(endpoint).concat(idPedido.toString()).concat("/buscar")).accept(MediaType.APPLICATION_JSON)
                .retrieve().bodyToMono(PedidoResponseDTO.class).block();

        return  pedidoDocumentMapper.toDomain(pedidoResponseDTO);
    }

    public PedidoDomain atualizarPedido(UUID idPedido, StatusPedido statusPedido) {

        WebClient webClient = WebClient.create();
        PedidoResponseDTO pedidoResponseDTO =  webClient.put()
                .uri(url.concat(endpoint).concat(idPedido.toString()).concat("/status/").concat(statusPedido.name()))
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PedidoResponseDTO.class)
                .block();

        return  pedidoDocumentMapper.toDomain(pedidoResponseDTO);
    }
}

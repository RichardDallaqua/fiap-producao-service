package com.fiap.producao.consumer;

import com.fiap.producao.commons.type.StatusPedido;
import com.fiap.producao.services.ProducaoService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProducaoConsumer {

    @Autowired
    private Gson gson;

    @Autowired
    private ProducaoService producaoService;

    @RabbitListener(queues = {"${queues.confirma_producao}"})
    public void receive(@Payload String message){
        HashMap<String, String> mensagem = gson.fromJson(message, HashMap.class);
        var producao = fromPagamentoMessage(mensagem);
        producaoService.alterarStatusPedido(producao.getIdPedido(), StatusPedido.valueOf(producao.getStatus()));
    }

    private static ProducaoDTO fromPagamentoMessage(Map message){
        return ProducaoDTO.builder()
                .idPedido(UUID.fromString(message.get("idPedido").toString()))
                .status(message.get("status").toString())
                .build();
    }
}

package com.fiap.producao.commons.type;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.producao.commons.exception.StatusNotAllowedException;

import lombok.Getter;

@Getter
public enum StatusPedido {
    @JsonProperty("ABERTO")
    ABERTO(1, new Integer[] { 2, 3, 4, 5, 6 }),
    @JsonProperty("CANCELADO")
    CANCELADO(2, new Integer[] {}),
    @JsonProperty("RECEBIDO")
    RECEBIDO(3, new Integer[] { 2, 4, 5, 6 }),
    @JsonProperty("PREPARANDO_PEDIDO")
    PREPARANDO_PEDIDO(4, new Integer[] { 5, 6 }),
    @JsonProperty("RETIRAR_PEDIDO")
    RETIRAR_PEDIDO(5, new Integer[] { 6 }),
    @JsonProperty("PEDIDO_RETIRADO")
    PEDIDO_RETIRADO(6, new Integer[] {});

    private Integer order;
    private Integer[] allowedUpdate;

    StatusPedido(Integer order, Integer[] allowedUpdate) {
        this.order = order;
        this.allowedUpdate = allowedUpdate;
    }

    public static void verifyOrderOnUpdate(StatusPedido oldStatus, StatusPedido nextStatus) {
        if (!Arrays.asList(oldStatus.getAllowedUpdate()).contains(nextStatus.getOrder())) {
            throw new StatusNotAllowedException(
                    String.format("Status %s não pode ser atribuido a esse pedido", nextStatus.name()));
        }
    }

}

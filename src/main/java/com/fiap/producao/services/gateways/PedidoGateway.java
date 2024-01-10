package com.fiap.producao.services.gateways;

import java.util.UUID;

import com.fiap.producao.domain.PedidoDomain;

public interface PedidoGateway {
    PedidoDomain buscarPedido(UUID id);
}

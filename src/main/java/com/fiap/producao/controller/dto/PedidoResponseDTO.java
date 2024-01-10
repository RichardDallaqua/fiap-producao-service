package com.fiap.producao.controller.dto;

import com.fiap.producao.commons.type.StatusPagamento;

import com.fiap.producao.commons.type.StatusPedido;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class PedidoResponseDTO implements Serializable {
    private UUID id;
    private List<ProdutoResponseDTO> listaProdutos;
    private BigDecimal valorTotalDaCompra;
    private int quantidadeTotalDeItems;
    private String nomeCliente;
    private StatusPedido statusPedido;
    private StatusPagamento statusPagamento;
}

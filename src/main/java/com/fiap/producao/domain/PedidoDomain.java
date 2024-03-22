package com.fiap.producao.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.producao.commons.type.StatusPagamento;
import com.fiap.producao.commons.type.StatusPedido;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Data
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class PedidoDomain {
    private UUID id;
    private List<ProdutoDomain> listaProdutos;
    private BigDecimal valorTotalDaCompra;
    private int quantidadeTotalDeItems;
    private String nomeCliente;
    private StatusPedido statusPedido;
    private StatusPagamento statusPagamento;
}
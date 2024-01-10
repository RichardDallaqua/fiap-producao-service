package com.fiap.producao.controller.dto;

import com.fiap.producao.commons.type.CategoriaProduto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
public class ProdutoResponseDTO {
    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private CategoriaProduto categoria;
}

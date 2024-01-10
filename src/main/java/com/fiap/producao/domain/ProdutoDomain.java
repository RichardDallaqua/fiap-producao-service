package com.fiap.producao.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fiap.producao.commons.type.CategoriaProduto;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDomain {
    private UUID id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private CategoriaProduto categoria;
}

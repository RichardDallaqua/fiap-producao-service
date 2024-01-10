package com.fiap.producao.dataprovider.dto;

import com.fiap.producao.commons.type.StatusPedido;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AtualizarStatusPedidoDTO {
    StatusPedido statusPedido;
}

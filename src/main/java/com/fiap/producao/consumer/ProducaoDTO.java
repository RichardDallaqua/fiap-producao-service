package com.fiap.producao.consumer;

import com.fiap.producao.commons.type.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProducaoDTO {

    private UUID idPedido;
    private StatusPedido status;
}

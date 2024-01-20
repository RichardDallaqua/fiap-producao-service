package com.fiap.producao.services;

import com.fiap.producao.commons.exception.PaymentNotApprovedException;
import com.fiap.producao.commons.type.StatusPagamento;
import com.fiap.producao.commons.type.StatusPedido;
import com.fiap.producao.dataprovider.PedidoDataProvider;
import com.fiap.producao.domain.PedidoDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProducaoService {

    @Autowired
    private PedidoDataProvider pedidoGateway;

    public PedidoDomain listarDadosDoPedido(UUID idPedido) {
        return pedidoGateway.buscarPedido(idPedido);
    }

    public void alterarStatusPedido(UUID idPedido, StatusPedido statusPedido) {
        PedidoDomain pedido = pedidoGateway.buscarPedido(idPedido);

        if (!statusPedido.equals(StatusPedido.CANCELADO)
                && pedido.getStatusPagamento().equals(StatusPagamento.AGUARDANDO_PAGAMENTO)) {
            throw new PaymentNotApprovedException("Alteração de status não permitida, pois o pagamento não efetuado");
        }

        StatusPedido.verifyOrderOnUpdate(pedido.getStatusPedido(), statusPedido);

        pedidoGateway.atualizarPedido(idPedido, statusPedido);
    }

}

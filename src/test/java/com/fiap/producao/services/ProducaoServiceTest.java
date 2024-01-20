package com.fiap.producao.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.producao.commons.exception.PaymentNotApprovedException;
import com.fiap.producao.commons.type.StatusPedido;
import com.fiap.producao.dataprovider.PedidoDataProvider;
import com.fiap.producao.domain.PedidoDomain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@MockitoSettings
class ProducaoServiceTest {

    @InjectMocks
    private ProducaoService producaoService;
    @Mock
    private PedidoDataProvider producaoDataProvider;

    @Test
    public void buscarDadosPedidos() throws IOException {
        // Configurando o comportamento dos mocks
        ObjectMapper objectMapper = new ObjectMapper();
        PedidoDomain pedido = objectMapper.readValue(new File("src/test/resources/schemas/pedido.schema.json"), PedidoDomain.class);
        Mockito.when(producaoDataProvider.buscarPedido(Mockito.any())).thenReturn(pedido);

        // Executando o método a ser testado
        PedidoDomain pedidoResponse = producaoService.listarDadosDoPedido(Mockito.any());

        // Verificando o resultado
        Assertions.assertNotNull(pedido);
        Assertions.assertEquals(StatusPedido.ABERTO, pedidoResponse.getStatusPedido());
    }

    @Test
    void alterarStatusPedido() throws IOException {
        // Configurando o comportamento dos mocks
        ObjectMapper objectMapper = new ObjectMapper();
        PedidoDomain pedidoCancelado = objectMapper.readValue(new File("src/test/resources/schemas/pedido_cancelado.schema.json"), PedidoDomain.class);
        Mockito.when(producaoDataProvider.atualizarPedido(Mockito.any(), Mockito.any())).thenReturn(pedidoCancelado);
        PedidoDomain pedido = objectMapper.readValue(new File("src/test/resources/schemas/pedido.schema.json"), PedidoDomain.class);
        Mockito.when(producaoDataProvider.buscarPedido(Mockito.any())).thenReturn(pedido);

        // Executando o método a ser testado
        producaoService.alterarStatusPedido(UUID.randomUUID(), StatusPedido.CANCELADO);

        // Verificando o resultado
        Mockito.verify(producaoDataProvider, Mockito.times(1)).atualizarPedido(Mockito.any(), Mockito.any());
    }

    @Test
    void quandoAlterarStatusPedidoDeveraRetornarErroDeStatus() throws IOException {
        // Configurando o comportamento dos mocks
        ObjectMapper objectMapper = new ObjectMapper();
        PedidoDomain pedido = objectMapper.readValue(new File("src/test/resources/schemas/pedido.schema.json"), PedidoDomain.class);
        Mockito.when(producaoDataProvider.buscarPedido(Mockito.any())).thenReturn(pedido);

        // Executando o método a ser testado
        Assertions.assertThrows(PaymentNotApprovedException.class, () -> {
            producaoService.alterarStatusPedido(UUID.randomUUID(), StatusPedido.PREPARANDO_PEDIDO);
        });
    }
}

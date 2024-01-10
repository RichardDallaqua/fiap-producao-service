package com.fiap.producao.controller;

import com.fiap.producao.commons.type.StatusPedido;
import com.fiap.producao.domain.PedidoDomain;
import com.fiap.producao.services.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{idPedido}/buscar")
    public ResponseEntity<PedidoDomain> listarDadosPedido(@PathVariable("idPedido") UUID idPedido) {
        return ResponseEntity.ok(pedidoService.listarDadosDoPedido(idPedido));
    }

    @PutMapping("/{idPedido}/status/{status}")
    public ResponseEntity<PedidoDomain> alterarStatus(@PathVariable("idPedido") UUID idPedido,
            @PathVariable("status") StatusPedido statusPedido) {
        pedidoService.alterarStatusPedido(idPedido, statusPedido);
        return ResponseEntity.noContent().build();
    }
}

package com.fiap.producao.dataprovider;

import com.fiap.producao.controller.dto.PedidoResponseDTO;
import com.fiap.producao.controller.dto.ProdutoResponseDTO;
import com.fiap.producao.domain.PedidoDomain;
import com.fiap.producao.domain.ProdutoDomain;
import com.fiap.producao.domain.ProdutoDomain.ProdutoDomainBuilder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-15T18:30:55-0300",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.9 (Azul Systems, Inc.)"
)
@Component
public class PedidoDocumentMapperImpl implements PedidoDocumentMapper {

    @Override
    public PedidoDomain toDomain(PedidoResponseDTO response) {
        if ( response == null ) {
            return null;
        }

        PedidoDomain pedidoDomain = new PedidoDomain();

        pedidoDomain.setId( response.getId() );
        pedidoDomain.setListaProdutos( produtoResponseDTOListToProdutoDomainList( response.getListaProdutos() ) );
        pedidoDomain.setValorTotalDaCompra( response.getValorTotalDaCompra() );
        pedidoDomain.setQuantidadeTotalDeItems( response.getQuantidadeTotalDeItems() );
        pedidoDomain.setNomeCliente( response.getNomeCliente() );
        pedidoDomain.setStatusPedido( response.getStatusPedido() );
        pedidoDomain.setStatusPagamento( response.getStatusPagamento() );

        return pedidoDomain;
    }

    protected ProdutoDomain produtoResponseDTOToProdutoDomain(ProdutoResponseDTO produtoResponseDTO) {
        if ( produtoResponseDTO == null ) {
            return null;
        }

        ProdutoDomainBuilder produtoDomain = ProdutoDomain.builder();

        produtoDomain.id( produtoResponseDTO.getId() );
        produtoDomain.nome( produtoResponseDTO.getNome() );
        produtoDomain.descricao( produtoResponseDTO.getDescricao() );
        produtoDomain.preco( produtoResponseDTO.getPreco() );
        produtoDomain.categoria( produtoResponseDTO.getCategoria() );

        return produtoDomain.build();
    }

    protected List<ProdutoDomain> produtoResponseDTOListToProdutoDomainList(List<ProdutoResponseDTO> list) {
        if ( list == null ) {
            return null;
        }

        List<ProdutoDomain> list1 = new ArrayList<ProdutoDomain>( list.size() );
        for ( ProdutoResponseDTO produtoResponseDTO : list ) {
            list1.add( produtoResponseDTOToProdutoDomain( produtoResponseDTO ) );
        }

        return list1;
    }
}

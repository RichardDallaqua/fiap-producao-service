package com.fiap.producao.dataprovider;

import com.fiap.producao.controller.dto.PedidoResponseDTO;
import com.fiap.producao.domain.PedidoDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PedidoDocumentMapper {

    PedidoDomain toDomain(PedidoResponseDTO response);
}
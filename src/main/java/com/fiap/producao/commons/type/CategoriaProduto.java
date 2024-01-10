package com.fiap.producao.commons.type;

import java.util.Arrays;

import com.fiap.producao.commons.exception.InvalidTypeException;

public enum CategoriaProduto {
    ACOMPANHAMENTO, BEBIDA, LANCHE;

    public static void existsInValues(String categoria) {
        if (Arrays.stream(values()).noneMatch(x -> x.name().equalsIgnoreCase(categoria))) {
            throw new InvalidTypeException(String.format("Categoria de produto %s é inválida", categoria));
        }
    }
}

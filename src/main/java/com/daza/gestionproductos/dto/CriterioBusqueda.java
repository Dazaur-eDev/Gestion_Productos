package com.daza.gestionproductos.dto;

public record CriterioBusqueda(
        String marca,
        String nombre,
        Integer precioMin,
        Integer precioMax
) {
}

package com.daza.gestionproductos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoUpdateDTO {
    private String nombre;
    private String marca;
    private String fechaElaboracion;
    private boolean estaDisponible;
    private int precio;
}

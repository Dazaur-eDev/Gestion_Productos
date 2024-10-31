package com.daza.gestionproductos.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String marca;
    private LocalDate fechaElaboracion;
    private boolean estaDisponible;
    private int precio;
}

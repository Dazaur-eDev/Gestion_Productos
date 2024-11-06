package com.daza.gestionproductos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoCreateDTO {

    private String nombre;
    private String marca;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate fechaElaboracion;
    private boolean estaDisponible;
    private int precio;
}
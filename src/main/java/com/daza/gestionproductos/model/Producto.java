package com.daza.gestionproductos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "productos")
public class Producto {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @Column(name = "nombre", nullable = false)
     private String nombre;

     @Column(name = "marca", nullable = false)
     private String marca;

     @Column(name = "fecha_elaboracion", nullable = false)
     private LocalDate fechaElaboracion;

     @Column(name = "disponible")
     private Boolean estaDisponible;

     @Column(name="precio", nullable = false)
     private Integer precio;


}

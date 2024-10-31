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
     //En postgres identity te genera una sequence por defecto de 1 en 1
     //si quieres definir sequence distinta, sequence
     //claro a nivel de db, defines seq
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
     private boolean estaDisponible;

     @Column(name="precio", nullable = false)
     private int precio;


}

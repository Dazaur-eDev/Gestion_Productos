package com.daza.gestionproductos.dto;

import com.daza.gestionproductos.model.Producto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ProductoMapper {

    public Producto toEntity(ProductoCreateDTO createDTO){
        Producto producto = new Producto();
        producto.setNombre(createDTO.getNombre());
        producto.setMarca(createDTO.getMarca());
        LocalDate fecha = createDTO.getFechaElaboracion();
        producto.setFechaElaboracion(fecha);
        producto.setEstaDisponible(createDTO.isEstaDisponible());
        producto.setPrecio(createDTO.getPrecio());
        return producto;
    }

    //🤷‍♂️
    public Producto toEntity(ProductoUpdateDTO updateDTO){
        Producto producto = new Producto();
        producto.setNombre(updateDTO.getNombre());
        producto.setMarca(updateDTO.getMarca());
        LocalDate fecha = updateDTO.getFechaElaboracion();
        producto.setFechaElaboracion(fecha);
        producto.setEstaDisponible(updateDTO.getEstaDisponible());
        producto.setPrecio(updateDTO.getPrecio());
        return producto;
    }

    public ProductoDTO toDto(Producto producto){
        ProductoDTO productoDTO = new ProductoDTO();
        productoDTO.setId(producto.getId());
        productoDTO.setNombre(producto.getNombre());
        productoDTO.setMarca(producto.getMarca());
        productoDTO.setFechaElaboracion(producto.getFechaElaboracion());
        productoDTO.setEstaDisponible(producto.getEstaDisponible());
        productoDTO.setPrecio(producto.getPrecio());
        return productoDTO;
    }


}

package com.daza.gestionproductos.service;

import com.daza.gestionproductos.dto.ProductoCreateDTO;
import com.daza.gestionproductos.dto.ProductoDTO;
import com.daza.gestionproductos.dto.ProductoUpdateDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductoService {

    ProductoDTO createProducto (ProductoCreateDTO createDTO);
    ProductoDTO updateProducto (Long id, ProductoUpdateDTO updateDTO);
    void deleteProducto (Long id);
    Page<ProductoDTO> busquedaProductoNombre(String nombre, Pageable pageable);
    Page<ProductoDTO> busquedaProductoMarca(String marca, Pageable pageable);
    Page<ProductoDTO> busquedaProductoRangoPrecio(int precioMin, int precioMax, Pageable pageable);
    Page<ProductoDTO> listarProductos(Pageable pageable);
    ProductoDTO buscarProductoPorId(Long id);

}

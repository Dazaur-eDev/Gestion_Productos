package com.daza.gestionproductos.repository;

import com.daza.gestionproductos.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    Page<Producto> findProductoByMarca(String marca, Pageable pageable );
    Page<Producto> findProductoByPrecioBetween(int precioMin, int precioMax, Pageable pageable);
}

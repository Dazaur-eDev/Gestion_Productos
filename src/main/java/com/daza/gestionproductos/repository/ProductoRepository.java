package com.daza.gestionproductos.repository;

import com.daza.gestionproductos.model.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    Page<Producto> findAll(Pageable pageable);
    Page<Producto> findByNombreContainingIgnoreCase(String nombre, Pageable pageable);
    Page<Producto> findByMarcaContainingIgnoreCase(String marca, Pageable pageable );
    Optional<Producto> findById(Long id);
    Page<Producto> findProductoByPrecioBetween(int precioMin, int precioMax, Pageable pageable);
    //Page<Producto> findByNombreContainingIgnoreCaseAndMarcaContainingIgnoreCaseAndPrecioBetween(String nombre, String marca, int precioMin, int precioMax, Pageable pageable);
}

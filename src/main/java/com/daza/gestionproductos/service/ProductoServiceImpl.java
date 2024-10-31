package com.daza.gestionproductos.service;

import com.daza.gestionproductos.dto.ProductoCreateDTO;
import com.daza.gestionproductos.dto.ProductoDTO;
import com.daza.gestionproductos.dto.ProductoMapper;
import com.daza.gestionproductos.dto.ProductoUpdateDTO;
import com.daza.gestionproductos.model.Producto;
import com.daza.gestionproductos.repository.ProductoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public ProductoDTO createProducto(ProductoCreateDTO createDTO) {
        Producto productoInsertar = productoMapper.toEntity(createDTO);
        Producto productoGuardado = productoRepository.save(productoInsertar);
        return productoMapper.toDto(productoGuardado);
    }

    @Override
    public ProductoDTO updateProducto(ProductoUpdateDTO updateDTO) {
        return null;
    }

    @Override
    public void deleteProducto(Long id) {

    }

    @Override
    public Page<ProductoDTO> busquedaProductoNombre(String nombre, Pageable pageable) {

        Page<Producto> productos = productoRepository.findByNombreContainingIgnoreCase(nombre, PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))));

        /*
        * La clase Page nos proporciona un método 'map', que mantiene lo necesario de la paginación y solo transforma
        * el contenido, por lo que no es necesario el mapeo manual.
        * */
        return productos.map(productoMapper::toDto);
    }

    @Override
    public Page<ProductoDTO> busquedaProductoMarca(String marca, Pageable pageable) {
        return null;
    }

    @Override
    public Page<ProductoDTO> busquedaProductoRangoPrecio(int precioMin, int precioMax, Pageable pageable) {
        return null;
    }
}

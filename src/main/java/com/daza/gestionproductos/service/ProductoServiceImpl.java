package com.daza.gestionproductos.service;

import com.daza.gestionproductos.dto.*;
import com.daza.gestionproductos.model.Producto;
import com.daza.gestionproductos.repository.ProductoBusqueda;
import com.daza.gestionproductos.repository.ProductoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final ProductoBusqueda productoBusqueda;

    @Override
    public ProductoDTO createProducto(ProductoCreateDTO createDTO) {
        Producto productoInsertar = productoMapper.toEntity(createDTO);
        Producto productoGuardado = productoRepository.save(productoInsertar);
        return productoMapper.toDto(productoGuardado);
    }

    @Override
    public ProductoDTO updateProducto(Long id, ProductoUpdateDTO updateDTO) {

        //Encontrar a modificar la entidad por id:
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con id: " + id));

        if(updateDTO.getNombre() != null){
            producto.setNombre(updateDTO.getNombre());
        }
        if(updateDTO.getMarca()!=null){
            producto.setMarca(updateDTO.getMarca());
        }
        if(updateDTO.getFechaElaboracion()!=null){
            producto.setFechaElaboracion(updateDTO.getFechaElaboracion());
        }
        if(updateDTO.getEstaDisponible() !=null){
            producto.setEstaDisponible(updateDTO.getEstaDisponible());
        }
        if(updateDTO.getPrecio()!=null){
            producto.setPrecio(updateDTO.getPrecio());
        }
        Producto updatedProduct = productoRepository.save(producto);

        return productoMapper.toDto(updatedProduct);
    }

    @Override
    public void deleteProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con id: " + id));
        productoRepository.delete(producto);
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

        Page<Producto> productos = productoRepository.findByMarcaContainingIgnoreCase(marca, PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))));


        return productos.map(productoMapper::toDto);
    }

    @Override
    public Page<ProductoDTO> busquedaProductoRangoPrecio(int precioMin, int precioMax, Pageable pageable) {

        Page<Producto> productos = productoRepository.findProductoByPrecioBetween(precioMin,precioMax, PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))));

        return productos.map(productoMapper::toDto);
    }

    @Override
    public Page<ProductoDTO> listarProductos(Pageable pageable) {
        Page<Producto> all = productoRepository.findAll(PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                pageable.getSortOr(Sort.by(Sort.Direction.ASC, "id"))));

        return all.map(productoMapper::toDto);
    }

    @Override
    public ProductoDTO buscarProductoPorId(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        return productoMapper.toDto(producto);
    }

    @Override
    public Page<ProductoDTO> busqueda(CriterioBusqueda criterioBusqueda, Pageable pageable) {
        return productoBusqueda.busquedaFiltrada(criterioBusqueda, pageable)
                               .map(productoMapper::toDto);
    }

//    @Override
//    public Page<ProductoDTO> busqueda(String nombre, String marca, int precioMin, int precioMax, Pageable pageable) {
//        Page<Producto> granNombreBusqueda = productoRepository.findByNombreContainingIgnoreCaseAndMarcaContainingIgnoreCaseAndPrecioBetween(nombre, marca, precioMin, precioMax, pageable);
//        return granNombreBusqueda.map(productoMapper::toDto);
//    }


}

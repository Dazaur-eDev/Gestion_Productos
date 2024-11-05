package com.daza.gestionproductos.controller;

import com.daza.gestionproductos.dto.ProductoCreateDTO;
import com.daza.gestionproductos.dto.ProductoDTO;
import com.daza.gestionproductos.dto.ProductoUpdateDTO;
import com.daza.gestionproductos.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoRestController {

    private final ProductoService productoService;

    @PostMapping("/create")
    public ResponseEntity<ProductoDTO> insertarProducto(@RequestBody ProductoCreateDTO productoCreateDTO) {
        ProductoDTO productoDTO = productoService.createProducto(productoCreateDTO);
        return ResponseEntity.ok(productoDTO); //created
    }

    @GetMapping(params = "nombre")
    // ?nombre=hola @RequestParam
    // /api/{id}/ @PathVariable
    public ResponseEntity<Page<ProductoDTO>> listarProductosNombre(@RequestParam String nombre,
                                                                   Pageable pageable) {
        return ResponseEntity.ok(productoService.busquedaProductoNombre(nombre, pageable));
    }

    @GetMapping(params = "marca")
    public ResponseEntity<Page<ProductoDTO>> buscarPorMarca(@RequestParam String marca,
                                                            Pageable pageable) {
        return ResponseEntity.ok(productoService.busquedaProductoMarca(marca, pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> editarProducto(@PathVariable Long id, @RequestBody ProductoUpdateDTO updateDTO){
        return ResponseEntity.ok(productoService.updateProducto(id, updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/busqueda")
    public ResponseEntity<Page<ProductoDTO>> filtroPorFechas(@RequestParam(value = "precio-min") int precioMin,
                                                             @RequestParam(value = "precio-max") int precioMax,
                                                             Pageable pageable){

        return ResponseEntity.ok(productoService.busquedaProductoRangoPrecio(precioMin, precioMax, pageable));
    }

    @GetMapping("/lista")
    public ResponseEntity<Page<ProductoDTO>> allProductos(Pageable pageable){
        Page<ProductoDTO> productoDTOS = productoService.listarProductos(pageable);
        return ResponseEntity.ok(productoDTOS);
    }
}

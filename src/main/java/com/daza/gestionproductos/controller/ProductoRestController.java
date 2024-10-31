package com.daza.gestionproductos.controller;

import com.daza.gestionproductos.dto.ProductoCreateDTO;
import com.daza.gestionproductos.dto.ProductoDTO;
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
        return ResponseEntity.ok(productoDTO);
    }

    @GetMapping
    // ?nombre=hola @RequestParam
    // /api/{id}/ @PathVariable
    public ResponseEntity<Page<ProductoDTO>> listarProductosNombre(@RequestParam String nombre,Pageable pageable) {
        return ResponseEntity.ok(productoService.busquedaProductoNombre(nombre, pageable));
    }

}

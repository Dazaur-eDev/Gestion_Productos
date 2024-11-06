package com.daza.gestionproductos.controller;

import com.daza.gestionproductos.dto.ProductoCreateDTO;
import com.daza.gestionproductos.dto.ProductoDTO;
import com.daza.gestionproductos.dto.ProductoUpdateDTO;
import com.daza.gestionproductos.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoMvcController {

    private final ProductoService productoService;

    @GetMapping
    public String listarProductos(Pageable pageable,
                                  Model model,
                                  @RequestParam(required = false) String filtroNombre,
                                  @RequestParam(required = false) String filtroMarca,
                                  @RequestParam(required = false) Integer precioMin,
                                  @RequestParam(required = false) Integer precioMax
    ){
        Page<ProductoDTO> productoDTOS = null;
        if (filtroNombre != null && !filtroNombre.trim().isEmpty()) {
            productoDTOS = productoService.busquedaProductoNombre(filtroNombre, pageable);
        } else if (filtroMarca != null && !filtroMarca.trim().isEmpty()) {
            productoDTOS = productoService.busquedaProductoMarca(filtroMarca, pageable);
        } else if (precioMin != null && precioMax != null) {
            productoDTOS = productoService.busquedaProductoRangoPrecio(precioMin, precioMax, pageable);
        } else {
            productoDTOS = productoService.listarProductos(pageable);
        }
        model.addAttribute("productos", productoDTOS);
        return "productos";

    }

    @GetMapping("/crear-producto")
    public String crearProducto(Model model){
        model.addAttribute("productoCreateDTO", new ProductoCreateDTO());
        return "crearProducto";
    }

    @PostMapping("/crear-producto")
    public String crearProducto(@ModelAttribute ProductoCreateDTO productoCreateDTO){
        productoService.createProducto(productoCreateDTO);
        return "redirect:/productos";
    }

    @PostMapping("/{id}/eliminar")
    public String eliminarProducto(@PathVariable  Long id){
        productoService.deleteProducto(id);
        return "redirect:/productos";
    }

    @GetMapping("/{id}/editar")
    public String editarProducto(@PathVariable Long id, Model model){
        ProductoDTO productoDTO = productoService.buscarProductoPorId(id);
        model.addAttribute("productoUpdateDTO", productoDTO);
        return "editarProducto";
    }

    @PostMapping("/{id}/editar")
    public String editarProducto(@PathVariable Long id,
                                @ModelAttribute ProductoUpdateDTO productoUpdateDTO) {

        productoService.updateProducto(id, productoUpdateDTO);
        return "redirect:/productos";

    }

}

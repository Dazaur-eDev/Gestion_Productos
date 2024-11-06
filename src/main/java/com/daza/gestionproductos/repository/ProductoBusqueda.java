package com.daza.gestionproductos.repository;

import com.daza.gestionproductos.dto.CriterioBusqueda;
import com.daza.gestionproductos.model.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class ProductoBusqueda {

    /**
     * EntityManager es la interfaz principal de JPA que gestiona el ciclo de vida de las entidades
     * y nos permite realizar operaciones con la base de datos
     */
    private final EntityManager entityManager;

    /**
     * Método principal que implementa una búsqueda filtrada con paginación
     *
     * @param criterioBusqueda - Objeto que contiene todos los posibles filtros de búsqueda
     * @param pageable         - Objeto que contiene la información de paginación (número de página, tamaño, ordenamiento)
     * @return Page<Producto> - Retorna una página de productos que coinciden con los criterios
     */
    public Page<Producto> busquedaFiltrada(CriterioBusqueda criterioBusqueda, Pageable pageable) {

        // Consulta base JPQL para obtener productos
        // JPQL es similar a SQL pero opera sobre entidades en lugar de tablas
        String jpql =
                "SELECT p" +
                " FROM Producto p" +
                " WHERE ";

        // Consulta para contar el total de resultados (necesario para la paginación)
        String countJpql =
                "SELECT COUNT(DISTINCT p)" +
                " FROM Producto p" +
                " WHERE ";

        // Lista que almacenará las condiciones dinámicas de la búsqueda
        List<String> partesJpql = new ArrayList<>();

        // Condición siempre verdadera para evitar problemas con el WHERE cuando no hay filtros
        partesJpql.add("1=1");

        // Mapa que almacena los valores de los parámetros que se usarán en la consulta
        Map<String, Object> parametros = new HashMap<>(); //TODO

        // Por cada criterio de búsqueda presente, agregamos la condición JPQL correspondiente
        // y guardamos su valor en el mapa de parámetros
        if (criterioBusqueda.nombre() != null) {
            // UPPER convierte todo a mayúsculas para búsqueda case-insensitive
            // || es el operador de concatenación en JPQL
            // % permite coincidencias parciales (contiene)
            partesJpql.add("UPPER(p.nombre) LIKE UPPER('%' || :nombre || '%')");
            parametros.put("nombre", criterioBusqueda.nombre());
        }
        if (criterioBusqueda.marca() != null) {
            partesJpql.add("UPPER(p.marca) LIKE UPPER('%' || :marca || '%')");
            parametros.put("marca", criterioBusqueda.marca());
        }
        if (criterioBusqueda.precioMin() != null) {
            partesJpql.add("p.precio >= :precioMin");
            parametros.put("precioMin", criterioBusqueda.precioMin());
        }
        if (criterioBusqueda.precioMax() != null) {
            partesJpql.add("p.precio <= :precioMax");
            parametros.put("precioMax", criterioBusqueda.precioMax());
        }
        //Si necesitamos mas criterios de busqueda, se modifica el dto y se agrega otro condicional aca.


        // Unimos todas las condiciones con AND para formar la cláusula WHERE completa
        String where = String.join(" AND ", partesJpql);


        // Primero ejecutamos la consulta de conteo para saber el total de resultados
        TypedQuery<Long> queryConteo = entityManager.createQuery(countJpql + where, Long.class);
        // Inyectamos los valores de los parámetros en la consulta
        parametros.forEach(queryConteo::setParameter);
        Long total = queryConteo.getSingleResult();


        // Construimos y ejecutamos la consulta principal con los mismos criterios
        TypedQuery<Producto> queryDatos = entityManager.createQuery(jpql + where + construirOrdenamiento(pageable), Producto.class);
        parametros.forEach(queryDatos::setParameter);

        // Configuración de la paginación
        queryDatos.setFirstResult((int) pageable.getOffset()); // Punto de inicio de la página
        queryDatos.setMaxResults(pageable.getPageSize()); // Cantidad de elementos por página

        // Obtenemos los resultados y construimos el objeto Page con la información de paginación
        List<Producto> resultadoDatos = queryDatos.getResultList();
        return new PageImpl<>(resultadoDatos, pageable, total);

    }

    /**
     * Construye la cláusula ORDER BY de la consulta basada en la información de ordenamiento
     * proporcionada por el objeto Pageable
     *
     * @param pageable - Contiene información sobre el ordenamiento deseado
     * @return String - Cláusula ORDER BY construida, o cadena vacía si no hay ordenamiento
     */
    private String construirOrdenamiento(Pageable pageable) {
        return pageable.getSort().isSorted()
                ? pageable.getSort().stream()
                          .map(order -> "p." + order.getProperty() + " " + order.getDirection())
                          .collect(Collectors.joining(", ", " ORDER BY ", ""))
                : "";
    }

}

**Enunciado del Proyecto: Sistema de Gestión de Productos**

Desarrollar una aplicación web en Java utilizando el framework Spring Boot y Spring Data JPA con base de datos PostgreSQL para la gestión de productos. El sistema implementará un CRUD que permitirá realizar operaciones de creación, lectura, actualización y eliminación de productos, con funcionalidades adicionales de paginación y filtrado en el listado.

### Requerimientos Específicos

1. **Modelo de Datos (Entidad Producto):**
    - **Atributos del Producto:**
        - `id` (Long): identificador único del producto.
        - `nombre` (String): nombre del producto.
        - `marca` (String): marca del producto.
        - `fechaElaboracion` (Date): fecha de elaboración del producto.
        - `estaDisponible` (Boolean): indica si el producto está disponible.
        - `precio` (Double): precio del producto.
    - El sistema debe contar con un esquema de validación de estos atributos y restricciones de unicidad en el nombre del producto, si corresponde.

2. **Capas de la Aplicación:**
    - **Modelo**: Define la entidad `Producto` que mapea la tabla en la base de datos.
    - **Repositorio**: Interfaces de Spring Data JPA para la interacción con PostgreSQL.
    - **DTOs (Data Transfer Objects)**: Manejo de datos de entrada y salida para las operaciones CRUD.
    - **Servicios**: Implementación de la lógica de negocio, como el guardado, actualización, eliminación y búsqueda de productos.
    - **Controladores**: Endpoints REST para exponer las operaciones CRUD y filtros adicionales.
    - **Template (Vistas)**: Utilizar un motor de plantillas (por ejemplo, Thymeleaf) para la visualización de los productos y las operaciones de CRUD.

3. **Funcionalidades CRUD:**
    - **Crear Producto**: Formulario para ingresar los datos del producto.
    - **Leer Producto**: Visualización de productos individuales y listado general.
    - **Actualizar Producto**: Edición de datos de un producto existente.
    - **Eliminar Producto**: Eliminación de un producto de la lista.

4. **Listados y Filtros:**
    - **Paginación**: Implementar paginación para el listado de productos.
    - **Filtros**: Implementar al menos dos filtros:
        - Filtrar por `marca`.
        - Filtrar por `precio` dentro de un rango específico.
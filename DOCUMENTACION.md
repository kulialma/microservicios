Documentación del Proyecto de Microservicios

Este proyecto implementa dos microservicios con Spring Boot:

micro_productos: gestión de productos.

micro_inventario: gestión de inventario y compras.

1. Descripción General

El sistema permite registrar productos, gestionar inventario y realizar compras. Los microservicios se comunican vía REST y utilizan una API Key para autenticación en las peticiones.

2. Arquitectura

micro_productos expone endpoints para crear y consultar productos.

micro_inventario consume el microservicio de productos para validar información y gestiona el stock.

Comunicación vía HTTP con encabezado X-API-KEY.

Puertos recomendados:

micro_productos: localhost:8082/inventario

micro_inventario: localhost:8081/productos/public

3. Endpoints

micro_productos

POST /productos

Crea un producto.

Request:

{
  "nombre": "Laptop",
  "precio": 3500.0
}

Response:

{
  "data": {
    "type": "producto",
    "id": 1,
    "attributes": {
      "nombre": "Laptop",
      "precio": 3500.0
    }
  }
}

GET /productos/{id}

Consulta un producto por ID.

micro_inventario

POST /inventario

Crea un registro de inventario.

Request:

{
  "productoId": 1,
  "cantidad": 20
}

GET /inventario

Lista todo el inventario.

GET /inventario/producto/{productoId}

Consulta la cantidad disponible de un producto.

Response:

{
  "data": {
    "type": "inventario",
    "id": 1,
    "attributes": {
      "producto": {
        "id": 1,
        "nombre": "Laptop",
        "precio": 3500.0
      },
      "cantidadDisponible": 20
    }
  }
}

PUT /inventario/{productoId}?cantidad=10

Actualiza la cantidad disponible de un producto.

POST /inventario/compra?productoId=1&cantidad=5

Registra una compra y descuenta stock.

Response:

{
  "data": {
    "type": "compra",
    "attributes": {
      "producto": {
        "id": 1,
        "nombre": "Laptop",
        "precio": 3500.0
      },
      "cantidadComprada": 5,
      "cantidadRestante": 15
    }
  }
}

4. Configuración

Archivo application.properties debe contener:

api.key=MI_API_KEY_SECRETA

Dependencias principales:

Spring Boot Starter Web

Spring Boot Starter Data JPA

H2 Database

Spring Boot Starter Test

5. Pruebas

FlujoCompraIntegrationTest valida:

Creación de producto.

Creación de inventario.

Compra exitosa.

Error por inventario insuficiente.

Error por producto inexistente.

InventarioControllerTest valida endpoints de inventario.

ProductosControllerTest valida endpoints de productos.

6. Reportes de Cobertura

Después de ejecutar mvn verify, se generan reportes de Jacoco:

micro_productos/target/site/jacoco/index.html
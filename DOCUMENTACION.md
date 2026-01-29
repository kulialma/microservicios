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

7. flujo y entorno de variables en postman 

aqui se puede probar todo el proyecto sin tener que configurar manualmente


//flujo
// nombre del archivo para crear y importar en el postman ---> microservicios.postman_collection.json

//codigo
{
  "info": {
    "name": "Microservicios Inventario y Productos",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "item": [
    {
      "name": "Productos",
      "item": [
        {
          "name": "Crear Producto",
          "request": {
            "method": "POST",
            "header": [
              { "key": "Content-Type", "value": "application/json" },
              { "key": "X-API-KEY", "value": "{{apiKey}}" }
            ],
            "url": "{{baseUrlProductos}}/productos",
            "body": {
              "mode": "raw",
              "raw": "{ \"nombre\": \"Laptop\", \"precio\": 3500.0 }"
            }
          }
        },
        {
          "name": "Consultar Producto",
          "request": {
            "method": "GET",
            "header": [
              { "key": "X-API-KEY", "value": "{{apiKey}}" }
            ],
            "url": "{{baseUrlProductos}}/productos/1"
          }
        }
      ]
    },
    {
      "name": "Inventario",
      "item": [
        {
          "name": "Crear Inventario",
          "request": {
            "method": "POST",
            "header": [
              { "key": "Content-Type", "value": "application/json" },
              { "key": "X-API-KEY", "value": "{{apiKey}}" }
            ],
            "url": "{{baseUrlInventario}}/inventario",
            "body": {
              "mode": "raw",
              "raw": "{ \"productoId\": 1, \"cantidad\": 20 }"
            }
          }
        },
        {
          "name": "Listar Inventario",
          "request": {
            "method": "GET",
            "header": [
              { "key": "X-API-KEY", "value": "{{apiKey}}" }
            ],
            "url": "{{baseUrlInventario}}/inventario"
          }
        },
        {
          "name": "Consultar Cantidad Producto",
          "request": {
            "method": "GET",
            "header": [
              { "key": "X-API-KEY", "value": "{{apiKey}}" }
            ],
            "url": "{{baseUrlInventario}}/inventario/producto/1"
          }
        },
        {
          "name": "Actualizar Cantidad",
          "request": {
            "method": "PUT",
            "header": [
              { "key": "X-API-KEY", "value": "{{apiKey}}" }
            ],
            "url": "{{baseUrlInventario}}/inventario/1?cantidad=10"
          }
        },
        {
          "name": "Registrar Compra",
          "request": {
            "method": "POST",
            "header": [
              { "key": "X-API-KEY", "value": "{{apiKey}}" }
            ],
            "url": "{{baseUrlInventario}}/inventario/compra?productoId=1&cantidad=5"
          }
        }
      ]
    }
  ]
}



//entorno de variables
// nombre del archivo para crear y importar en el postman --->  microservicios.postman_environment.json

//codigo

{
  "id": "microservicios-env",
  "name": "Microservicios Environment",
  "values": [
    {
      "key": "baseUrlProductos",
      "value": "http://localhost:8081",
      "enabled": true
    },
    {
      "key": "baseUrlInventario",
      "value": "http://localhost:8082",
      "enabled": true
    },
    {
      "key": "apiKey",
      "value": "MI_API_KEY_SECRETA",
      "enabled": true
    }
  ],
  "_postman_variable_scope": "environment",
  "_postman_exported_at": "2026-01-29T12:05:00.000Z",
  "_postman_exported_using": "Postman/10.0.0"
}

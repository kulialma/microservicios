Proyecto de Microservicios

Este repositorio contiene dos microservicios desarrollados con Spring Boot:

micro_productos: Gestión de productos.

micro_inventario: Gestión de inventario y compras.

Requisitos

Java 17 (versión recomendada: 17.0.17)

Maven 3.9.x (versión recomendada: 3.9.6)

Git 2.x (versión recomendada: 2.44 o superior)

Clonar el repositorio

git clone https://github.com/kulialma/microservicios.git
 cd mi-proyecto-microservicios

Arrancar los microservicios

Microservicio Productos

cd micro_productos
 mvn spring-boot:run

Microservicio Inventario

cd micro_inventario
 mvn spring-boot:run

Ejecutar pruebas

Microservicio Productos

cd micro_productos
 mvn test

Microservicio Inventario

cd micro_inventario
 mvn test

Ejecutar verificación

Microservicio Productos

cd micro_productos
 mvn verify

Microservicio Inventario

cd micro_inventario
 mvn verify

Reportes de cobertura

Después de ejecutar mvn verify, se generan los reportes de Jacoco en:

micro_productos/target/site/jacoco/index.html
 micro_inventario/target/site/jacoco/index.html
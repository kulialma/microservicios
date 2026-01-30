# 📦 Proyecto de Microservicios con Spring Boot y Docker Compose

Este proyecto contiene dos microservicios independientes (**micro_productos** y **micro_inventario**) desarrollados con **Spring Boot 3.5.10**, empaquetados con **Maven 3.6.3** y ejecutados en contenedores Docker.  
La ejecución se realiza en **Ubuntu/WSL2** utilizando **Docker Engine 29.2.0** y **Docker Compose v5.0.2**.

---

## 🚀 Requisitos previos

- **Ubuntu/WSL2** configurado correctamente.
- **Java 17 (OpenJDK 17.0.17)** instalado:
  ```bash
  sudo apt update
  sudo apt install -y openjdk-17-jdk
Maven 3.6.3 instalado:

bash
sudo apt install -y maven
Docker Engine 29.2.0 y Docker Compose v5.0.2 instalados.

Verificar versiones:

bash
java -version
javac -version
mvn -v
docker --version
docker compose version
📂 Estructura del proyecto
Código
mi-proyecto-microservicios/
├── micro_productos/
│   ├── pom.xml
│   ├── Dockerfile
│   └── src/...
├── micro_inventario/
│   ├── pom.xml
│   ├── Dockerfile
│   └── src/...
├── README.md
├── DOCUMENTACION.md
└── docker-compose.yml
🛠️ Compilación de los microservicios
Productos
bash
cd micro_productos
mvn clean package
Inventario
bash
cd ../micro_inventario
mvn clean package
👉 Esto genera los .jar en la carpeta target/.

🐳 Dockerfile de cada microservicio
micro_productos
dockerfile
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]
micro_inventario
dockerfile
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8082
ENTRYPOINT ["java","-jar","app.jar"]
⚙️ Archivo docker-compose.yml
Ubicado en la raíz del proyecto:

yaml
services:
  productos:
    build: ./micro_productos
    ports:
      - "8081:8081"
    container_name: micro_productos

  inventario:
    build: ./micro_inventario
    ports:
      - "8082:8082"
    container_name: micro_inventario
▶️ Levantar los microservicios
Desde la raíz del proyecto:

bash
docker compose up --build
👉 Esto construye las imágenes y arranca ambos contenedores.

✅ Verificación
Productos: http://localhost:8081/productos/public

Inventario: http://localhost:8082/inventario

Prueba con curl:

bash
curl http
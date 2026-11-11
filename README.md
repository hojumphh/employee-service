# Employee Service API - Prueba Técnica Invex

---

## 🛠 Stack Tecnológico
* **Lenguaje:** Java 17
* **Framework:** Spring Boot 2.7.18
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** MySQL
* **Gestor de Dependencias:** Maven
* **Librerías Clave:** Lombok, SpringDoc OpenAPI (Swagger), JaCoCo (Cobertura)
* **Testing:** JUnit 5, Mockito, AssertJ, MockMvc

---

## 📂 Estructura del Proyecto
El proyecto sigue una separación clara de responsabilidades por capas:
```text
src/
├── main/
│   java/com/hotoniel/employee_service/
│   ├── controller/      # Endpoints REST (Controladores)
│   ├── service/         # Lógica de negocio (Interfaces e Implementaciones)
│   ├── repository/      # Interfaces de acceso a datos (Spring Data JPA)
│   ├── domain/          # Entidades JPA y Enums (ej. Gender)
│   ├── dto/             # Objetos de transferencia (Request / Response con campo 'maternal')
│   ├── mapper/          # Mapeadores de entidades a DTOs y viceversa
│   └── exception/       # Manejo global de excepciones (GlobalExceptionHandler)
└── resources/
    └── application.yaml # Configuración de conexión y perfiles

```
---

## ⚙️ Requisitos Previos

* Asegúrate de tener instalado en tu entorno local:
* JDK 17 o superior.
* Maven 3.8+ (o puedes usar el wrapper incluido ./mvnw).

---
## 🚀 MySQL Server ejecutándose localmente.

🚀 Guía de Instalación y Ejecución
### 1. Clonar el repositorio y configurar la Base de Datos
   Crea una base de datos en MySQL con el nombre que prefieras (por ejemplo, employee_db):
```sql
   CREATE DATABASE employee_db;
```

### 2. Configurar credenciales
   Abre el archivo src/main/resources/application.yaml y verifica que los datos de tu conexión a MySQL sean correctos:
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/employee_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
    username: tu_usuario
    password: tu_contraseña
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: false
```

### 3. Compilar el proyecto
Ejecuta el siguiente comando en la raíz del proyecto para descargar las dependencias y compilar:

```bash
mvn clean install
```

### 4. Ejecutar la Aplicación
Para poner en marcha el servidor localmente, ejecuta:

```bash
mvn spring-boot-run
```
La API estará disponible por defecto en: http://localhost:8080/api/v1/employees

---
## 📖Documentación de la API (Swagger UI)

Una vez que la aplicación esté en ejecución, puedes explorar y probar todos los endpoints de manera interactiva a través de la interfaz de Swagger UI integrada:
👉 http://localhost:8080/swagger-ui/index.html


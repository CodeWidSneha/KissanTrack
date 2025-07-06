# 🌾 KissanTrack – Product & Supplier Management API

KissanTrack is a Spring Boot-based RESTful API project designed to manage agricultural **products** and **suppliers** efficiently. It provides endpoints to perform CRUD operations and query data in a structured way, ideal for farm inventory or agri-business applications.

---

## 🚀 Features

- 🔄 Full CRUD operations for **Products** and **Suppliers**
- 🔍 Search products by name prefix
- 📦 Data handled via **JPA repositories**
- 📗 JSON-based request/response
- 📁 Layered architecture (Controller → Service → Repository)

---

## 🛠️ Tech Stack

- **Backend**: Java, Spring Boot
- **Database**: MySQL
- **ORM**: Spring Data JPA
- **Build Tool**: Maven
- **API Tool**: Postman

---


---

## 📫 API Endpoints Overview

### Product APIs
- `GET /product` – Get all products
- `GET /product/{id}` – Get product by ID
- `POST /product` – Create a new product
- `GET /products/startsWith/{prefix}` – Get products with names starting with prefix

### Supplier APIs
- `GET /supplier` – Get all suppliers
- `POST /suppliers` – Add a new supplier
- `GET /suppliers/{id}` – Get supplier by id

---

## 📦 Running the Project

1. Clone the repo
   ```bash
   git clone https://github.com/CodeWidSneha/KissanTrack.git
   cd KissanTrack
   
2. Update DB credentials and other properties in application.properties
```
spring.application.name=kissanTrack
server.port=8081
server.error.include-stacktrace=never
server.error.include-path=never
# ======= DATABASE CONFIGURATION =======

spring.datasource.url=jdbc:mysql://localhost:3306/kissan?useSSL=false
spring.datasource.username=root
spring.datasource.password=<your-pass>
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.datasource.hikari.connection-timeout=4000
spring.datasource.hikari.maximum-pool-size=10

# ======= JPA / HIBERNATE CONFIG =======


spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```
3. Run the application
```
mvn spring-boot:run

```

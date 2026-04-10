# Person-Aadhaar CRUD Application

A production-style **RESTful API** built with **Spring Boot**, **Hibernate JPA**, and **MySQL** that manages a OneToOne relationship between a Person and their Aadhaar details. Fully deployed on Railway with a live demo endpoint.

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.5-brightgreen?style=flat-square&logo=springboot)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?style=flat-square&logo=mysql)
![Deployed](https://img.shields.io/badge/Deployed-Railway-purple?style=flat-square)

---

## 🔗 Live Demo

> **Base URL:** `https://person-aadhaar-crud-production.up.railway.app`

Test with Postman or any REST client — no authentication required.

---

## 📌 Features

- Full **CRUD operations** for Person and linked Aadhaar record
- **OneToOne JPA mapping** with cascaded save and delete
- **DTO pattern** — request/response objects separate from database entities
- **Duplicate validation** — rejects duplicate emails and Aadhaar numbers
- **RESTful design** — proper HTTP verbs and status codes (201, 200, 404, 400)
- **MySQL** database with Hibernate auto schema generation
- **CORS enabled** for frontend integration

---

## 🛠 Tech Stack

| Layer       | Technology                          |
|-------------|-------------------------------------|
| Language    | Java 17                             |
| Framework   | Spring Boot 3.2.5                   |
| ORM         | Hibernate / Spring Data JPA         |
| Database    | MySQL 8.0                           |
| Build Tool  | Maven                               |
| Deployment  | Railway (with Railway MySQL plugin) |
| Testing     | Postman                             |

---

## 📁 Project Structure

```
src/main/java/com/siddhesh/aadhaar/
├── AadhaarApplication.java       ← Entry point
├── entity/
│   ├── Person.java               ← @Entity with @OneToOne
│   └── Aadhaar.java              ← @Entity with @JoinColumn
├── repository/
│   ├── PersonRepository.java     ← JpaRepository + custom queries
│   └── AadhaarRepository.java
├── service/
│   ├── PersonService.java        ← Interface
│   └── PersonServiceImpl.java    ← Business logic
├── controller/
│   └── PersonController.java     ← @RestController, all 6 endpoints
└── dto/
    ├── PersonRequest.java        ← Incoming request body
    └── PersonResponse.java       ← Outgoing response body
```

---

## 🔌 API Endpoints

| Method   | Endpoint                          | Description                        |
|----------|-----------------------------------|------------------------------------|
| `POST`   | `/api/persons`                    | Create a new Person + Aadhaar      |
| `GET`    | `/api/persons`                    | Get all persons with Aadhaar data  |
| `GET`    | `/api/persons/{id}`               | Get one person by ID               |
| `GET`    | `/api/persons/email/{email}`      | Get person by email                |
| `PUT`    | `/api/persons/{id}`               | Update person and Aadhaar details  |
| `DELETE` | `/api/persons/{id}`               | Delete person and linked Aadhaar   |

---

## 📋 Sample Request & Response

### POST `/api/persons`

**Request Body:**
```json
{
  "name": "Siddhesh Bhatkar",
  "age": 23,
  "email": "siddhesh@example.com",
  "city": "Mumbai",
  "aadhaarNumber": "123456789012",
  "address": "Alibag, Maharashtra - 402201",
  "dateOfBirth": "15-08-2001"
}
```

**Response (201 Created):**
```json
{
  "personId": 1,
  "name": "Siddhesh Bhatkar",
  "age": 23,
  "email": "siddhesh@example.com",
  "city": "Mumbai",
  "aadhaarNumber": "123456789012",
  "address": "Alibag, Maharashtra - 402201",
  "dateOfBirth": "15-08-2001"
}
```

### GET `/api/persons/1`

**Response (200 OK):**
```json
{
  "personId": 1,
  "name": "Siddhesh Bhatkar",
  "age": 23,
  "email": "siddhesh@example.com",
  "city": "Mumbai",
  "aadhaarNumber": "123456789012",
  "address": "Alibag, Maharashtra - 402201",
  "dateOfBirth": "15-08-2001"
}
```

### DELETE `/api/persons/1`

**Response (200 OK):**
```
Person with ID 1 deleted successfully.
```

---

## ⚙️ Local Setup

### Prerequisites
- Java 17+
- MySQL 8.0
- Maven (bundled with Eclipse or install separately)

### 1. Clone the repository
```bash
git clone https://github.com/Siddhesh-Bhatkar/person-aadhaar-crud.git
cd person-aadhaar-crud
```

### 2. Create MySQL database
```sql
CREATE DATABASE aadhaar_db;
```

### 3. Configure application properties
```bash
cp src/main/resources/application.properties.example \
   src/main/resources/application.properties
```
Edit `application.properties` and set your MySQL username and password.

### 4. Run the application
```bash
# Using Maven
mvn spring-boot:run

# Or in Eclipse: Right-click project → Run As → Spring Boot App
```

### 5. Test the API
Import the Postman collection or manually hit:
```
http://localhost:8080/api/persons
```

---

## 🚀 Deployment (Railway)

This project is deployed on [Railway](https://railway.app) with a Railway MySQL plugin.

Environment variables set on Railway:
```
MYSQLHOST       → auto-injected by Railway MySQL plugin
MYSQLPORT       → auto-injected
MYSQLDATABASE   → auto-injected
MYSQLUSER       → auto-injected
MYSQLPASSWORD   → auto-injected
PORT            → auto-injected
```

The `application.properties` reads these via `${VARIABLE_NAME}` syntax — no hardcoded credentials in code.

---

## 🧠 Key Concepts Demonstrated

- **OneToOne JPA Mapping** — `@OneToOne`, `@JoinColumn`, `mappedBy`, `CascadeType.ALL`
- **Repository Pattern** — custom query methods using Spring Data JPA naming convention
- **Service Layer** — interface + implementation separation (SOLID principles)
- **DTO Pattern** — decoupling API contract from database schema
- **Transaction Management** — `@Transactional` and `readOnly = true` for queries
- **RESTful API Design** — correct HTTP methods, status codes, and URI naming

---

## 👤 Author

**Siddhesh Madhav Bhatkar**
- GitHub: [@Siddhesh-Bhatkar](https://github.com/Siddhesh-Bhatkar)
- LinkedIn: [siddhesh-bhatkar](https://linkedin.com/in/siddhesh-bhatkar)
- Email: siddheshbhatkar114@gmail.com

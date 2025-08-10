# Pathwise Backend

Pathwise Backend is a **Spring Boot** application that uses **MongoDB** as its database.  
It follows clean code conventions for naming and organization, and provides API documentation using **Swagger**.

---

## 🚀 Features

- Career Path Analysis
- Skill Gap Identification
- Job Opportunity Matching
- JWT-based authentication and authorization
- Resume & Interview Optimization
- Predictive Analytics
- Gamification & Engagement
- Customizable Learning Paths
- Industry-Specific Modules
- Community & Networking

---

## 📦 Tech Stack

- **Java 21** (or compatible version)
- **Spring Boot**
- **Spring Data MongoDB**
- **Spring Security + JWT**
- **Swagger** 
- **Maven**

---

## 🗂️ Project Structure & Naming Conventions

- **Packages**: Use **PascalCase**
    - Example: `com.example.pathwisebackend.Controllers`
- **Classes / Files**: Use **PascalCase**
    - Example: `TestController.java`
- **Variables & Methods**: Use **camelCase**
    - Example: `accessToken`, `refreshToken`

---

## ⚙️ Configuration

### Environment Variables

| Variable Name | Description | Example                                                                                                           |
|---------------|-------------|-------------------------------------------------------------------------------------------------------------------|
| `DATABASE_URL` | MongoDB connection string | `mongodb://localhost:27017/pathwise` or `mongodb+srv://<db_username>:<db_password>@pathwise.pnjnora.mongodb.net/` |

You can set it in your terminal or `.env` file:

```bash
DATABASE_URL="mongodb://localhost:27017/pathwise"

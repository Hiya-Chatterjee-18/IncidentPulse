# ⚡ Smart Incident Management & Analytics Platform

![Java 21](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791)
![MongoDB](https://img.shields.io/badge/MongoDB-brightgreen)
![Redis](https://img.shields.io/badge/Redis-red)
![Apache Kafka](https://img.shields.io/badge/Apache_Kafka-black)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-FF6600)
![PySpark](https://img.shields.io/badge/PySpark-E25A1C)
![Apache Airflow](https://img.shields.io/badge/Apache_Airflow-017CEE)
![Docker](https://img.shields.io/badge/Docker-2496ED)
![React](https://img.shields.io/badge/React_18-61DAFB)

> An end-to-end enterprise-style platform for intelligent IT incident
> management, real-time event processing, distributed ETL, SLA monitoring,
> and operational analytics.

---

## 📌 Overview

The **Smart Incident Management & Analytics Platform** manages the complete
incident lifecycle — from reporting and intelligent prioritization to
assignment, SLA tracking, resolution, Root Cause Analysis (RCA), knowledge
base creation, and analytics.

The project combines **Java backend engineering, distributed systems,
real-time data processing, ETL, orchestration, caching, and analytics** in a
single enterprise-style application.

---

## 🚀 Key Features

- 🔐 JWT authentication with Role-Based Access Control
- 👥 Employee, Support Specialist, and Admin roles
- 🧠 Intelligent incident priority classification
- 🔍 Duplicate incident detection using Jaccard similarity
- ⏱️ Priority-based SLA tracking and breach detection
- ⚡ Real-time incident event streaming using Apache Kafka
- 📨 Asynchronous task processing using RabbitMQ
- 🔥 PySpark-based ETL and data transformation
- 🧹 Data-quality validation with DLQ handling
- 🔄 Apache Airflow pipeline orchestration
- ⚡ Redis caching for frequently accessed analytics
- 📊 MTTR, SLA compliance, workload, and incident analytics
- 📚 RCA and Knowledge Base management
- 📝 Audit logging and centralized exception handling
- 🐳 Dockerized multi-service environment
- 📖 REST APIs with OpenAPI / Swagger
- 🧪 JUnit, Mockito, and Postman testing

---

# 🏗️ Architecture

```mermaid
graph TD

    USER[Users] --> UI[React 18 + Vite]

    UI -->|REST API + JWT| API[Java 21 + Spring Boot 3]

    API --> PG[(PostgreSQL)]
    API --> MONGO[(MongoDB)]
    API --> REDIS[(Redis)]

    API -->|Incident Events| KAFKA[Apache Kafka]
    API -->|Async Tasks| RMQ[RabbitMQ]

    KAFKA --> SPARK[PySpark ETL]

    SPARK -->|Invalid Records| DLQ[DLQ]
    SPARK -->|Processed Data| S3[(AWS S3 / Parquet)]

    AIRFLOW[Apache Airflow] -->|Orchestration| SPARK

    API --> LOGS[Logging / Monitoring]

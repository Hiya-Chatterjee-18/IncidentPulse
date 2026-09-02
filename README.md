# 🚨 Smart Incident Management & Analytics Platform

![Java 21](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue)
![MongoDB](https://img.shields.io/badge/Database-MongoDB-brightgreen)
![Redis](https://img.shields.io/badge/Cache-Redis-red)
![Apache Kafka](https://img.shields.io/badge/Event%20Streaming-Apache%20Kafka-black)
![RabbitMQ](https://img.shields.io/badge/Async%20Messaging-RabbitMQ-orange)
![PySpark](https://img.shields.io/badge/ETL-PySpark-red)
![Apache Airflow](https://img.shields.io/badge/Orchestration-Apache%20Airflow-blue)
![Docker](https://img.shields.io/badge/DevOps-Docker-blue)
![React](https://img.shields.io/badge/Frontend-React-61DAFB)

> An enterprise-grade incident management and analytics platform combining **Spring Boot microservices, event-driven architecture, real-time data streaming, PySpark ETL, Airflow orchestration, SLA management, and analytics**.

---

## 🎯 Overview

Smart Incident Management & Analytics Platform is designed to manage the complete incident lifecycle:

**Report → Prioritize → Detect Duplicates → Assign → Track SLA → Resolve → Analyze**

The platform combines transactional backend services with an event-driven data pipeline to transform operational incident data into actionable analytics.

---

## ✨ Key Features

- 🔐 JWT-based authentication and role-based access control
- 🚨 Intelligent incident priority classification
- 🔍 Duplicate incident detection using similarity analysis
- 👥 Role-based incident assignment and management
- ⏱️ SLA tracking with priority-based response and resolution targets
- 💬 Incident comments, resolution and audit history
- 📚 Knowledge base for incident resolution
- ⚡ Kafka-based incident event streaming
- 📨 RabbitMQ asynchronous event processing
- 🧠 Redis-based analytics caching
- 📊 Analytics dashboards and operational metrics
- 🔄 PySpark-based ETL and data transformation
- 🛡️ Data quality validation and exception handling
- 📅 Apache Airflow workflow orchestration
- 🐳 Docker Compose-based development environment
- 📖 Swagger/OpenAPI API documentation

---

## 🏗️ Architecture

```mermaid
flowchart LR

    UI[React Frontend]

    API[Java 21<br/>Spring Boot]

    AUTH[JWT Security]

    DB1[(PostgreSQL)]
    DB2[(MongoDB)]
    CACHE[(Redis)]

    KAFKA[Apache Kafka]
    RMQ[RabbitMQ]

    SPARK[PySpark ETL]
    AIRFLOW[Apache Airflow]

    DATA[(Analytics Data)]

    UI --> API
    API --> AUTH
    API --> DB1
    API --> DB2
    API --> CACHE

    API --> KAFKA
    API --> RMQ

    KAFKA --> SPARK
    AIRFLOW --> SPARK
    SPARK --> DATA

    DATA --> API

# 🚨 IncidentPulse: Smart Incident Management & Analytics Platform

![Java 21](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue)
![MongoDB](https://img.shields.io/badge/Database-MongoDB-brightgreen)
![Redis](https://img.shields.io/badge/Cache-Redis-red)
![Apache Kafka](https://img.shields.io/badge/Event%20Streaming-Apache%20Kafka-black)
![RabbitMQ](https://img.shields.io/badge/Async%20Messaging-RabbitMQ-orange)
![PySpark](https://img.shields.io/badge/Data%20Engineering-PySpark-red)
![Apache Airflow](https://img.shields.io/badge/Orchestration-Apache%20Airflow-blue)
![Docker](https://img.shields.io/badge/Containerization-Docker-blue)
![React](https://img.shields.io/badge/Frontend-React-61DAFB)

> An enterprise-style incident management and analytics platform combining **Java/Spring Boot, event-driven architecture, real-time data streaming, PySpark ETL, workflow orchestration, SLA management, data quality validation, and operational analytics**.

---

## 📌 Overview

The **Smart Incident Management & Analytics Platform** manages the complete incident lifecycle while transforming operational incident events into analytics-ready data.

The system combines a transactional backend with an event-driven data engineering pipeline to support:

**Report → Prioritize → Detect Duplicates → Assign → Track SLA → Resolve → Analyze**

It is designed around real-world engineering concerns such as **authentication, role-based access control, asynchronous messaging, event streaming, caching, data validation, exception handling, ETL, workflow orchestration, and analytics**.

---

## 🎯 Key Objectives

- Centralize incident reporting and resolution
- Automatically determine incident priority
- Detect potentially duplicate incidents
- Track response and resolution SLAs
- Support role-based incident operations
- Decouple operational processing from analytics workloads
- Stream incident lifecycle events
- Build analytics-ready datasets through ETL
- Validate data before downstream analytics
- Handle failed records through a DLQ workflow
- Provide actionable operational dashboards

---

## ✨ Key Features

### 🔐 Security & Access Control
- JWT-based authentication
- BCrypt password hashing
- Role-based access control
- Protected REST APIs
- Employee, Support Specialist, and IT Manager/Admin roles

### 🚨 Intelligent Incident Management
- Automated priority classification
- Business-rule-based priority engine
- Duplicate incident detection
- Incident assignment and workload management
- Incident comments and audit history
- Root-cause analysis and resolution tracking

### ⏱️ SLA Management

| Priority | Response SLA | Resolution SLA |
|----------|--------------:|---------------:|
| Critical | 1 hour | 4 hours |
| High | 2 hours | 8 hours |
| Medium | 4 hours | 24 hours |
| Low | 8 hours | 48 hours |

### ⚡ Event-Driven Processing
- Apache Kafka for incident lifecycle events
- RabbitMQ for asynchronous processing
- Kafka producer/consumer architecture
- Event-based separation between operational and analytical workloads

### 🧠 Data Engineering
- PySpark ETL pipelines
- Data cleaning and transformation
- Data enrichment
- Data quality validation
- Schema-based processing
- Failed-record / DLQ handling
- Analytics-ready datasets

### 📊 Analytics
- Incident volume analysis
- Priority distribution
- SLA compliance
- Resolution trends
- Agent workload
- Incident status analysis
- Recurring incident patterns

### 🚀 Engineering & DevOps
- Dockerized services
- Docker Compose development environment
- REST APIs
- Swagger/OpenAPI documentation
- JUnit 5 and Mockito testing
- Structured application logging
- Git/GitHub version control

---

# 🏗️ System Architecture

The platform follows a **layered, event-driven architecture** separating transactional incident operations from asynchronous processing and data analytics.

```mermaid
flowchart TB

    %% =========================
    %% USERS / PRESENTATION
    %% =========================

    subgraph USERS["Users"]
        EMP["Employee"]
        AGENT["Support Specialist"]
        ADMIN["IT Manager / Admin"]
    end

    subgraph FRONTEND["Presentation Layer"]
        UI["React 18 + Vite"]
    end

    EMP --> UI
    AGENT --> UI
    ADMIN --> UI

    %% =========================
    %% APPLICATION
    %% =========================

    subgraph BACKEND["Application Layer — Java 21 / Spring Boot 3"]

        SECURITY["JWT Authentication<br/>+ RBAC"]

        CONTROLLERS["REST Controllers"]

        INCIDENT["Incident Service"]
        PRIORITY["Intelligent Priority Engine"]
        DUPLICATE["Duplicate Classifier"]
        ANALYTICS["Analytics Service"]
        KNOWLEDGE["Knowledge Base Service"]

    end

    UI --> SECURITY
    SECURITY --> CONTROLLERS

    CONTROLLERS --> INCIDENT
    CONTROLLERS --> ANALYTICS
    CONTROLLERS --> KNOWLEDGE

    INCIDENT --> PRIORITY
    INCIDENT --> DUPLICATE

    %% =========================
    %% OPERATIONAL DATA
    %% =========================

    subgraph STORAGE["Operational Data Layer"]

        POSTGRES[("PostgreSQL<br/>Structured Data")]
        MONGO[("MongoDB<br/>Documents")]
        REDIS[("Redis<br/>Cache")]
    end

    INCIDENT --> POSTGRES
    INCIDENT --> MONGO
    KNOWLEDGE --> MONGO
    ANALYTICS --> REDIS

    %% =========================
    %% MESSAGING
    %% =========================

    subgraph MESSAGING["Event & Messaging Layer"]

        KAFKA["Apache Kafka<br/>Incident Events"]
        RABBIT["RabbitMQ<br/>Async Messaging"]
    end

    INCIDENT --> KAFKA
    INCIDENT --> RABBIT

    %% =========================
    %% DATA ENGINEERING
    %% =========================

    subgraph DATA_ENGINEERING["Data Engineering Layer"]

        CONSUMER["Kafka Consumer"]

        SPARK["PySpark<br/>ETL"]

        TRANSFORM["Cleaning & Transformation"]

        QUALITY["Data Quality Validation"]

        DLQ["DLQ / Failed Records"]

        DATASET[("Analytics Dataset")]

        AIRFLOW["Apache Airflow<br/>Workflow Orchestration"]
    end

    KAFKA --> CONSUMER
    CONSUMER --> SPARK
    SPARK --> TRANSFORM
    TRANSFORM --> QUALITY

    QUALITY -->|Valid| DATASET
    QUALITY -->|Invalid| DLQ

    AIRFLOW -.->|Schedules / Orchestrates| SPARK
    AIRFLOW -.->|Controls Workflows| QUALITY

    %% =========================
    %% ANALYTICS
    %% =========================

    DATASET --> ANALYTICS
    ANALYTICS --> UI

# ⚡ Smart Incident Management & Analytics Platform

![Java 21](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.5-green)
![PostgreSQL](https://img.shields.io/badge/Operational_DB-PostgreSQL-336791)
![MongoDB](https://img.shields.io/badge/Document_DB-MongoDB-brightgreen)
![Redis](https://img.shields.io/badge/Cache-Redis_Sub--15ms-red)
![Apache Kafka](https://img.shields.io/badge/Event_Streaming-Apache_Kafka-black)
![RabbitMQ](https://img.shields.io/badge/Async_Queue-RabbitMQ-FF6600)
![PySpark](https://img.shields.io/badge/ETL_Pipeline-PySpark-E25A1C)
![Airflow](https://img.shields.io/badge/Orchestration-Apache_Airflow-017CEE)
![Docker](https://img.shields.io/badge/DevOps-Docker_Compose-2496ED)
![React](https://img.shields.io/badge/Frontend-React_18-61DAFB)
![Status](https://img.shields.io/badge/Rating-9.7%2F10_Enterprise-success)

An enterprise-grade outage tracking, real-time event streaming, PySpark ETL, and SLA analytics platform built with **Java 21, Spring Boot 3, PostgreSQL, MongoDB, Redis, Apache Kafka, RabbitMQ, PySpark, Apache Airflow, Docker Compose, and React**.

---

## 🏗️ 1. High-Level Enterprise Architecture & Technology Stack

```mermaid
graph TD
    UI[React 18 + Vite SPA Frontend] -->|REST API / JWT Auth| API[Java 21 + Spring Boot 3 Microservice]
    
    subgraph Core Persistence & Security
        API -->|Relational Data| PG[(PostgreSQL Operational DB)]
        API -->|Unstructured RCA / KB| MONGO[(MongoDB Document Store)]
        API -->|Sub-15ms Analytics| REDIS[(Redis Cache Store)]
    end
    
    subgraph Event-Driven Messaging Layer
        API -->|Task Queues: Notifications & SLA Alerts| RMQ[RabbitMQ Task Queue]
        API -->|Lifecycle Stream: INCIDENT_CREATED, RESOLVED| KAFKA[Apache Kafka Broker]
    end
    
    subgraph Data Engineering & Analytics Pipeline
        KAFKA -->|Real-Time Event Ingestion| SPARK[PySpark Distributed ETL Engine]
        SPARK -->|Schema Validation & DLQ| DLQ[Dead Letter Queue / Error Log]
        SPARK -->|Partitioned Parquet Data Lake| S3[(AWS S3 / Parquet Data Lake)]
        AIRFLOW[Apache Airflow DAG] -->|Daily Orchestration & KPI Recalculation| SPARK
    end

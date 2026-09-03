# Enterprise System Architecture & Data Flow

The **Smart Incident Management & Analytics Platform** follows a **layered, event-driven architecture** designed to decouple transactional incident processing from analytical workloads, asynchronous messaging, and data pipeline ETL.

---

## 🏗️ 1. Complete System Architecture Diagram

```mermaid
flowchart TB

    %% =========================
    %% USERS / PRESENTATION
    %% =========================

    subgraph USERS["Users & Personas"]
        EMP["Employee / Reporter"]
        AGENT["Support Specialist"]
        ADMIN["IT Manager / Admin"]
    end

    subgraph FRONTEND["Presentation Layer — React 18 + Vite"]
        UI["Interactive React SPA<br/>(Kanban, SLA Timer, Analytics Dashboard)"]
    end

    EMP --> UI
    AGENT --> UI
    ADMIN --> UI

    %% =========================
    %% APPLICATION / BACKEND
    %% =========================

    subgraph BACKEND["Application Layer — Java 21 / Spring Boot 3"]

        SECURITY["JWT Authentication Filter<br/>+ Role-Based Access Control"]
        CONTROLLERS["REST Controllers<br/>(Incidents, Analytics, KB, Auth)"]

        INCIDENT["Incident Service"]
        PRIORITY["Intelligent Priority Engine"]
        DUPLICATE["Duplicate Ticket Classifier"]
        ANALYTICS["Analytics Service"]
        KNOWLEDGE["Knowledge Base Service"]

    end

    UI -->|REST / JSON + JWT Token| SECURITY
    SECURITY --> CONTROLLERS

    CONTROLLERS --> INCIDENT
    CONTROLLERS --> ANALYTICS
    CONTROLLERS --> KNOWLEDGE

    INCIDENT --> PRIORITY
    INCIDENT --> DUPLICATE

    %% =========================
    %% OPERATIONAL DATA LAYER
    %% =========================

    subgraph STORAGE["Operational Data Layer"]

        POSTGRES[("PostgreSQL 15<br/>Structured Relational DB")]
        MONGO[("MongoDB<br/>Unstructured Document DB")]
        REDIS[("Redis Alpine<br/>Sub-15ms Cache")]
    end

    INCIDENT -->|Transactional CRUD| POSTGRES
    INCIDENT -->|Audit Logs| MONGO
    KNOWLEDGE -->|RCA Solutions| MONGO
    ANALYTICS -->|Cached Metrics| REDIS

    %% =========================
    %% MESSAGING LAYER
    %% =========================

    subgraph MESSAGING["Event & Asynchronous Messaging Layer"]

        KAFKA["Apache Kafka Broker<br/>(Topic: incident-lifecycle-events)"]
        RABBIT["RabbitMQ Task Queue<br/>(Queue: incident-notification-queue)"]
    end

    INCIDENT -->|Publish Lifecycle Events| KAFKA
    INCIDENT -->|Publish Task Jobs| RABBIT

    %% =========================
    %% DATA ENGINEERING LAYER
    %% =========================

    subgraph DATA_ENGINEERING["Data Engineering & Analytics Layer"]

        CONSUMER["Kafka Streaming Consumer"]

        SPARK["PySpark ETL Engine<br/>(Distributed Data Transformation)"]

        QUALITY["Data Quality Validator<br/>(Schema Enforcement & Validation)"]

        DLQ["Dead Letter Queue (DLQ)<br/>(Corrupted Record Log)"]

        DATASET[("Parquet Data Lake<br/>(Partitioned Analytics Dataset)")]

        AIRFLOW["Apache Airflow DAG<br/>(Daily Orchestration & KPI Calculation)"]
    end

    KAFKA --> CONSUMER
    CONSUMER --> SPARK
    SPARK --> QUALITY

    QUALITY -->|Valid Records| DATASET
    QUALITY -->|Invalid Records| DLQ

    AIRFLOW -.->|Schedules & Orchestrates| SPARK
    AIRFLOW -.->|Triggers Validation| QUALITY

    %% =========================
    %% ANALYTICS FEEDBACK
    %% =========================

    DATASET --> ANALYTICS
    ANALYTICS --> UI

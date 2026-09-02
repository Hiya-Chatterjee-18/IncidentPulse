## 🏗️ System Architecture

The platform follows a **layered, event-driven architecture** that separates transactional incident management from asynchronous processing and analytics.

```mermaid
flowchart TB

    %% ================= USER LAYER =================
    subgraph CLIENT["Presentation Layer"]
        EMP["Employee"]
        AGENT["Support Specialist"]
        ADMIN["IT Manager / Admin"]
        UI["React 18 + Vite"]
    end

    EMP --> UI
    AGENT --> UI
    ADMIN --> UI

    %% ================= API LAYER =================
    subgraph API["Application Layer — Java 21 / Spring Boot 3"]
        AUTH["Authentication & Authorization<br/>JWT + BCrypt"]
        IC["Incident Controller"]
        AC["Analytics Controller"]
        KB["Knowledge Base Controller"]
        AMC["Admin Controller"]

        IS["Incident Service"]
        APS["Intelligent Priority Engine"]
        DC["Duplicate Classifier"]
        AS["Analytics Service"]
        KBS["Knowledge Base Service"]
    end

    UI --> AUTH
    UI --> IC
    UI --> AC
    UI --> KB
    UI --> AMC

    IC --> IS
    IS --> APS
    IS --> DC
    AC --> AS
    KB --> KBS

    %% ================= DATA LAYER =================
    subgraph DATA["Operational Data Layer"]
        PG[("PostgreSQL<br/>Transactional Data")]
        MDB[("MongoDB<br/>Incident Documents")]
        REDIS[("Redis<br/>Caching")]
    end

    IS --> PG
    IS --> MDB
    AS --> REDIS
    KBS --> MDB

    %% ================= EVENT LAYER =================
    subgraph EVENTS["Event-Driven Messaging Layer"]
        KAFKA["Apache Kafka<br/>Incident Lifecycle Events"]
        RABBIT["RabbitMQ<br/>Async Processing"]
    end

    IS --> KAFKA
    IS --> RABBIT

    %% ================= DATA ENGINEERING =================
    subgraph DE["Data Engineering Layer"]
        SPARK["PySpark<br/>ETL & Transformations"]
        DQ["Data Quality Validation"]
        DLQ["DLQ / Exception Handling"]
        AIRFLOW["Apache Airflow<br/>Workflow Orchestration"]
    end

    KAFKA --> SPARK
    AIRFLOW --> SPARK
    SPARK --> DQ
    DQ -->|Valid Data| ANALYTICS[("Analytics Dataset")]
    DQ -->|Invalid Data| DLQ

    %% ================= ANALYTICS =================
    AS --> ANALYTICS

    ANALYTICS --> AC
    AC --> UI

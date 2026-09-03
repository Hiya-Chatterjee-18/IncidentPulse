
---

### `data-pipeline.md`
📂 **GitHub Repository Path:** `Smart-Incident-Management-System/docs/data-pipeline.md`  
💻 **Local Disk Path:** [`C:\Users\Lenovo\.gemini\antigravity\scratch\smart-incident-management-system\docs\data-pipeline.md`](file:///C:/Users/Lenovo/.gemini/antigravity/scratch/smart-incident-management-system/docs/data-pipeline.md)

```markdown
# Data Engineering Pipeline Specification

The data pipeline processes real-time incident lifecycle events through a **Kafka → PySpark → Airflow → Data Lake** architecture.

### Pipeline Stages:
1. **Event Ingestion**: Kafka Producer emits `INCIDENT_CREATED`, `STATUS_CHANGED`, `RESOLVED` events.
2. **Schema Validation**: PySpark validates incoming JSON schema and filters corrupted payloads.
3. **Dead Letter Queue (DLQ)**: Malformed payloads are safely diverted to `data/dlq/` for ELK log inspection.
4. **Analytical Transformations**: PySpark calculates rolling Mean Time to Resolve (MTTR) and SLA breach rates.
5. **Data Lake Storage**: Outputs partitioned Parquet datasets stored in `data/processed/`.

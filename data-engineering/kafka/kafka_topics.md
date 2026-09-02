# Apache Kafka Topics Architecture

| Topic Name | Partitions | Replication Factor | Retention | Description |
| :--- | :---: | :---: | :---: | :--- |
| `incident-lifecycle-events` | 3 | 1 | 7 Days | Primary durable stream for all incident lifecycle mutations (`CREATED`, `UPDATED`, `RESOLVED`). |
| `incident-sla-breaches` | 2 | 1 | 14 Days | High-priority stream capturing tickets breaching SLA resolution windows. |
| `incident-dlq-events` | 1 | 1 | 30 Days | Dead Letter Queue capturing schema validation failures & corrupted event payloads. |

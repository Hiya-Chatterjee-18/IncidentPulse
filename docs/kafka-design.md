# Apache Kafka Topic Design & Partitioning Strategy

### Topics & Partitions:
- `incident-lifecycle-events`: **3 Partitions** (Keyed by `incident_id` to maintain ordered state mutations).
- `incident-sla-breaches`: **2 Partitions** (Captures high-priority SLA breaches).
- `incident-dlq-events`: **1 Partition** (Dead Letter Queue for corrupted messages).

### Architectural Rationale:
Kafka acts as a durable, append-only log that decouples operational web processing from analytical batch & stream consumers.

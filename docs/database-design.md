# PostgreSQL & MongoDB Hybrid Database Schema Design

### 1. Relational Database (PostgreSQL):
Stores core transactional data requiring strict ACID compliance, foreign keys, and relational constraints:
- `incidents` (id, title, category, priority, status, reporter_id, assignee_id, sla_deadlines)
- `users` (id, name, email, password_hash, role, department, specialty)

### 2. Document Store (MongoDB):
Stores dynamic, schema-less unstructured documents:
- `knowledge_base` (id, title, category, summary, root_cause, resolution_steps, tags)
- `audit_logs` (actor, action, details, timestamp)

# PySpark ETL & Data Quality Validation Design

### Core Transformation Logic:
- **Schema Enforcement**: Inforces strict StructTypes on incoming raw event streams.
- **Null & Boundary Filtering**: Rejects missing IDs or negative resolution time values.
- **Aggregation Strategy**: Performs windowed aggregations calculating:
  - `avg_mttr_hours` = `AVG(resolution_time_min) / 60.0`
  - `sla_breach_rate` = `COUNT(sla_breached == true) / COUNT(total_incidents)`
- **Parquet Partitioning**: Output written as `data/processed/category=Database/*.parquet`.

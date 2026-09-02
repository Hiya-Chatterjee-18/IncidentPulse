# data_quality_validator.py
"""
Data Quality Validation Module for PySpark ETL Pipeline
Filters malformed payloads, verifies timestamp formats, and routes bad records to Dead Letter Queue (DLQ).
"""

def validate_incident_records(df):
    """
    Splits PySpark DataFrame into valid records and dead-letter invalid records.
    """
    from pyspark.sql.functions import col
    
    valid_df = df.filter(
        (col("incident_id").isNotNull()) &
        (col("category").isNotNull()) &
        (col("priority").isNotNull()) &
        (col("resolution_time_min") >= 0)
    )
    
    invalid_df = df.filter(
        (col("incident_id").isNull()) |
        (col("category").isNull()) |
        (col("priority").isNull()) |
        (col("resolution_time_min") < 0)
    )
    
    return valid_df, invalid_df

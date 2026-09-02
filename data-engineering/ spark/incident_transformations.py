# incident_transformations.py
"""
PySpark Analytical Transformations for SLA & MTTR KPI Calculation
"""

def compute_mttr_and_sla_kpis(df):
    """
    Computes rolling Mean Time to Resolve (MTTR) in hours and SLA compliance rates per category.
    """
    from pyspark.sql.functions import col, avg, count, when, round, current_timestamp
    
    return df.groupBy("category", "priority").agg(
        round(avg("resolution_time_min") / 60.0, 2).alias("avg_mttr_hours"),
        count("incident_id").alias("total_incidents"),
        count(when(col("sla_breached") == True, 1)).alias("sla_breach_count")
    ).withColumn("computed_at", current_timestamp())

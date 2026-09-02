# PySpark ETL Engine Configuration

```python
conf = SparkConf() \
    .setAppName("Smart-Incident-PySpark-ETL") \
    .set("spark.sql.shuffle.partitions", "4") \
    .set("spark.driver.memory", "2g") \
    .set("spark.executor.memory", "2g") \
    .set("spark.hadoop.fs.s3a.impl", "org.apache.hadoop.fs.s3a.S3AFileSystem")

# kafka_incident_producer.py
"""
Smart Incident Management & Analytics Platform: Apache Kafka Event Producer
-----------------------------------------------------------------------------
Streams real-time incident lifecycle events (INCIDENT_CREATED, STATUS_CHANGED, SLA_BREACH, INCIDENT_RESOLVED)
to Apache Kafka topic 'incident-lifecycle-events' for downstream PySpark ETL & analytics ingestion.
"""

import json
import time
import random
from datetime import datetime

try:
    from kafka import KafkaProducer
    KAFKA_AVAILABLE = True
except ImportError:
    KAFKA_AVAILABLE = False


INCIDENT_CATEGORIES = ["Database", "Backend Service", "Cloud Infrastructure", "Payment Gateway", "Authentication", "Network"]
PRIORITIES = ["CRITICAL", "HIGH", "MEDIUM", "LOW"]
EVENT_TYPES = ["INCIDENT_CREATED", "STATUS_CHANGED", "SLA_BREACH", "INCIDENT_RESOLVED"]

def generate_incident_event(event_id_num):
    category = random.choice(INCIDENT_CATEGORIES)
    priority = random.choice(PRIORITIES)
    event_type = random.choice(EVENT_TYPES)
    
    response_time_min = random.randint(5, 120)
    resolution_time_min = random.randint(30, 480)
    sla_breached = resolution_time_min > (240 if priority in ["CRITICAL", "HIGH"] else 1440)

    payload = {
        "event_id": f"EVT-{1000 + event_id_num}",
        "incident_id": f"INC-{5000 + (event_id_num % 50)}",
        "event_type": event_type,
        "category": category,
        "priority": priority,
        "reporter_dept": random.choice(["Engineering", "Finance", "Operations", "Customer Success"]),
        "response_time_min": response_time_min,
        "resolution_time_min": resolution_time_min,
        "sla_breached": sla_breached,
        "timestamp": datetime.utcnow().isoformat() + "Z"
    }
    return payload


def run_kafka_event_stream(topic="incident-lifecycle-events", bootstrap_servers="localhost:9092", num_events=30):
    print(f"⚡ Starting Incident Kafka Streaming Producer on topic '{topic}'...")
    
    producer = None
    if KAFKA_AVAILABLE:
        try:
            producer = KafkaProducer(
                bootstrap_servers=bootstrap_servers,
                value_serializer=lambda v: json.dumps(v).encode('utf-8')
            )
            print("✅ Connected to Apache Kafka Broker successfully!")
        except Exception as e:
            print(f"⚠️ Kafka connection fallback ({e}). Running in Event Simulation Mode.")

    for i in range(num_events):
        event = generate_incident_event(i + 1)
        if producer:
            producer.send(topic, value=event)
            print(f"[{i+1}/{num_events}] Published Kafka Event: {event['event_type']} | {event['incident_id']} | Priority: {event['priority']} | Breached: {event['sla_breached']}")
        else:
            print(f"[{i+1}/{num_events}] [SIMULATED KAFKA EVENT] -> {event['event_type']} for {event['incident_id']} ({event['category']})")
        
        time.sleep(0.3)

    if producer:
        producer.flush()
        print("✅ Finished streaming incident events to Kafka topic.")


if __name__ == "__main__":
    run_kafka_event_stream(num_events=25)

package com.incident.system.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;

@Component
public class KafkaIncidentEventProducer {

    private static final Logger log = LoggerFactory.getLogger(KafkaIncidentEventProducer.class);
    private static final String TOPIC = "incident-lifecycle-events";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public KafkaIncidentEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishLifecycleEvent(String eventType, String incidentId) {
        Map<String, Object> eventPayload = Map.of(
                "eventId", "evt_" + System.currentTimeMillis(),
                "eventType", eventType,
                "incidentId", incidentId,
                "timestamp", Instant.now().toString()
        );

        log.info("⚡ [KAFKA PRODUCER] Publishing event to topic '{}': {}", TOPIC, eventPayload);
        try {
            kafkaTemplate.send(TOPIC, incidentId, eventPayload);
        } catch (Exception e) {
            log.warn("⚠️ Kafka Connection Fallback: Simulated publishing event {}", eventPayload);
        }
    }
}

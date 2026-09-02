package com.incident.system.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaIncidentEventConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaIncidentEventConsumer.class);

    @KafkaListener(topics = "incident-lifecycle-events", groupId = "incident-analytics-group")
    public void consumeEvent(String message) {
        log.info("⚡ [KAFKA CONSUMER] Consumed incident lifecycle event: {}", message);
    }
}

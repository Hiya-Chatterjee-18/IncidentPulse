package com.incident.system.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;

@Component
public class RabbitMqEventPublisher {

    private static final Logger log = LoggerFactory.getLogger(RabbitMqEventPublisher.class);
    private static final String QUEUE_NAME = "incident-notification-queue";

    private final RabbitTemplate rabbitTemplate;

    public RabbitMqEventPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishIncidentEvent(String eventType, String incidentId) {
        Map<String, Object> taskPayload = Map.of(
                "taskId", "task_" + System.currentTimeMillis(),
                "eventType", eventType,
                "incidentId", incidentId,
                "timestamp", Instant.now().toString()
        );

        log.info("⚡ [RABBITMQ TASK QUEUE] Publishing task to queue '{}': {}", QUEUE_NAME, taskPayload);
        try {
            rabbitTemplate.convertAndSend(QUEUE_NAME, taskPayload);
        } catch (Exception e) {
            log.warn("⚠️ RabbitMQ Connection Fallback: Simulated task queue {}", taskPayload);
        }
    }
}

package com.incident.system.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    public static final String NOTIFICATION_QUEUE = "incident-notification-queue";
    public static final String SLA_ALERT_QUEUE = "incident-sla-alert-queue";

    @Bean
    public Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE, true);
    }

    @Bean
    public Queue slaAlertQueue() {
        return new Queue(SLA_ALERT_QUEUE, true);
    }
}

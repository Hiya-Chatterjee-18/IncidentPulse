package com.incident.system.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public static final String INCIDENT_EVENTS_TOPIC = "incident-lifecycle-events";

    @Bean
    public NewTopic incidentEventsTopic() {
        return TopicBuilder.name(INCIDENT_EVENTS_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}

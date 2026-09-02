package com.incident.system.config;

import com.incident.system.model.*;
import com.incident.system.repository.IncidentRepository;
import com.incident.system.repository.KnowledgeBaseRepository;
import com.incident.system.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final IncidentRepository incidentRepository;
    private final KnowledgeBaseRepository kbRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, IncidentRepository incidentRepository, KnowledgeBaseRepository kbRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.incidentRepository = incidentRepository;
        this.kbRepository = kbRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            User admin = new User("u-admin", "Alex Morgan", "alex.morgan@company.com", passwordEncoder.encode("password123"), UserRole.ADMIN, "IT Management", "System Admin", "https://api.dicebear.com/7.x/avataaars/svg?seed=Alex");
            User agent1 = new User("u-agent1", "David Chen", "david.chen@company.com", passwordEncoder.encode("password123"), UserRole.AGENT, "Infrastructure", "Database Specialist", "https://api.dicebear.com/7.x/avataaars/svg?seed=David");
            User reporter1 = new User("u-user1", "Sarah Jenkins", "sarah.j@company.com", passwordEncoder.encode("password123"), UserRole.REPORTER, "Finance", "Senior Accountant", "https://api.dicebear.com/7.x/avataaars/svg?seed=Sarah");

            userRepository.saveAll(List.of(admin, agent1, reporter1));
        }

        if (incidentRepository.count() == 0) {
            User reporter = userRepository.findByEmail("sarah.j@company.com").orElse(null);
            User agent = userRepository.findByEmail("david.chen@company.com").orElse(null);

            if (reporter != null && agent != null) {
                Instant now = Instant.now();
                Incident inc1 = new Incident();
                inc1.setId("INC-89210");
                inc1.setTitle("Production Payment Gateway 500 Outage");
                inc1.setCategory("Payment Gateway");
                inc1.setDescription("Payment API returning 500 internal server error for checkout transactions.");
                inc1.setPriority(Priority.CRITICAL);
                inc1.setStatus(Status.IN_PROGRESS);
                inc1.setImpact("Organization");
                inc1.setUrgency("Critical");
                inc1.setReporterId(reporter.getId());
                inc1.setReporterName(reporter.getName());
                inc1.setAssigneeId(agent.getId());
                inc1.setAssigneeName(agent.getName());
                inc1.setSlaResponseDeadline(now.plus(1, ChronoUnit.HOURS));
                inc1.setSlaResolveDeadline(now.plus(4, ChronoUnit.HOURS));

                incidentRepository.save(inc1);
            }
        }

        if (kbRepository.count() == 0) {
            KnowledgeBaseArticle kb1 = new KnowledgeBaseArticle(
                    "kb-101",
                    "Database Connection Pool Exhaustion Fix",
                    "Database",
                    "Increased maximum connection pool size from 20 to 100 in application.yml.",
                    "High traffic volume caused connection leak under peak checkout load.",
                    "Increased pool size and configured maxLifetime to 30 minutes.",
                    List.of("Database", "PostgreSQL", "Pool")
            );
            kbRepository.save(kb1);
        }
    }
}

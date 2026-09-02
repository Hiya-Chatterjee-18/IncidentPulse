package com.incident.system.repository;

import com.incident.system.model.Incident;
import com.incident.system.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, String> {
    List<Incident> findByStatus(Status status);
    List<Incident> findByCategory(String category);
    List<Incident> findByAssigneeId(String assigneeId);
}

package com.incident.system.repository;

import com.incident.system.model.KnowledgeBaseArticle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KnowledgeBaseRepository extends MongoRepository<KnowledgeBaseArticle, String> {
    List<KnowledgeBaseArticle> findByCategory(String category);
}

package com.incident.system.service;

import com.incident.system.model.KnowledgeBaseArticle;
import com.incident.system.repository.KnowledgeBaseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowledgeBaseService {

    private final KnowledgeBaseRepository kbRepository;

    public KnowledgeBaseService(KnowledgeBaseRepository kbRepository) {
        this.kbRepository = kbRepository;
    }

    public List<KnowledgeBaseArticle> getAllArticles() {
        return kbRepository.findAll();
    }
}

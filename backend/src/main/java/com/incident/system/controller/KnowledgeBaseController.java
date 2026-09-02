package com.incident.system.controller;

import com.incident.system.model.KnowledgeBaseArticle;
import com.incident.system.service.KnowledgeBaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/kb")
@CrossOrigin(origins = "*")
public class KnowledgeBaseController {

    private final KnowledgeBaseService kbService;

    public KnowledgeBaseController(KnowledgeBaseService kbService) {
        this.kbService = kbService;
    }

    @GetMapping
    public ResponseEntity<List<KnowledgeBaseArticle>> getAllArticles() {
        return ResponseEntity.ok(kbService.getAllArticles());
    }
}

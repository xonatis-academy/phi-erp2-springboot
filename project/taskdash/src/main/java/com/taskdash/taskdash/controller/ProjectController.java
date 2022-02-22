package com.taskdash.taskdash.controller;

import com.taskdash.taskdash.domain.Project;
import com.taskdash.taskdash.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {
    
    @Autowired
    private ProjectRepository projectRepository;

    // GET /api/v1/projects
    @GetMapping("/api/v1/projects")
    public @ResponseBody ResponseEntity<Iterable<Project>> list() {
        // On récupère la liste des projets en BDD grâce au repository
        Iterable<Project> projects = projectRepository.findAll();
        // On met ca dans le body de la reponse en disant OK (en gros pas d'erreur)
        return ResponseEntity.ok().body(projects);
    }

    
}

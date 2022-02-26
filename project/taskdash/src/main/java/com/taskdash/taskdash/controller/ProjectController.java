package com.taskdash.taskdash.controller;

import java.util.Optional;

import com.taskdash.taskdash.domain.Project;
import com.taskdash.taskdash.dto.ProjectRequest;
import com.taskdash.taskdash.repository.ProjectRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProjectController {

    @Autowired
    private ProjectRepository projectRepository;

    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping("/api/v1/projects")
    public @ResponseBody ResponseEntity<Iterable<Project>> find() {
        Iterable<Project> projects = projectRepository.findAll();
        return ResponseEntity.ok().body(projects);
    }

    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping("/api/v1/project/{id}")
    public @ResponseBody ResponseEntity<Project> getid(@PathVariable int id) {
        Optional<Project> result = projectRepository.findById(id);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();
        Project project = result.get();
        return ResponseEntity.ok().body(project);
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @PostMapping("/api/v1/project")
    public @ResponseBody ResponseEntity<Project> createproject(@RequestBody ProjectRequest requestDto) {
        Project project = new Project();
        project.setTitle(requestDto.getTitle());
        project.setDescription(requestDto.getDescription());
        projectRepository.save(project);
        return ResponseEntity.ok().body((project));
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @PutMapping("/api/v1/projects/{id}")
    public @ResponseBody ResponseEntity<Project> modifyProject(@PathVariable int id,
            @RequestBody ProjectRequest requestDto) {
        Optional<Project> result = projectRepository.findById(id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Project project = result.get();
        project.setTitle(requestDto.getTitle());
        project.setDescription(requestDto.getDescription());
        projectRepository.save(project);
        return ResponseEntity.ok().body((project));
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @DeleteMapping("/api/v1/projects/{id}")
    public @ResponseBody ResponseEntity<Project> delete(@PathVariable int id) {
        Optional<Project> result = projectRepository.findById(id);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();
        projectRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

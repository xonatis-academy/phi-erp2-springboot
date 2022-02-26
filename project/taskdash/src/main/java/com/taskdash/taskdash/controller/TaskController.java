package com.taskdash.taskdash.controller;

import java.util.Optional;

import com.taskdash.taskdash.domain.AppUser;
import com.taskdash.taskdash.domain.Project;
import com.taskdash.taskdash.domain.Task;
import com.taskdash.taskdash.dto.TaskAppUserRequest;
import com.taskdash.taskdash.dto.TaskRequest;
import com.taskdash.taskdash.repository.AppUserRepository;
import com.taskdash.taskdash.repository.ProjectRepository;
import com.taskdash.taskdash.repository.TaskRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AppUserRepository appUserRepository;

    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping("/api/v1/tasks")
    public @ResponseBody ResponseEntity<Iterable<Task>> find() {
        Iterable<Task> tasks = taskRepository.findAll();
        return ResponseEntity.ok().body(tasks);
    }

    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    @GetMapping("/api/v1/tasks/{id}")
    public @ResponseBody ResponseEntity<Task> getid(@PathVariable int id) {
        Optional<Task> result = taskRepository.findById(id);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();
        Task tasks = result.get();
        return ResponseEntity.ok().body(tasks);
    }

    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    @PostMapping("/api/v1/tasks")
    public @ResponseBody ResponseEntity<Task> createtasks(@RequestBody TaskRequest taskDto) {
        Task tasks = new Task();
        tasks.setTitle(taskDto.getTitle());
        tasks.setDescription(taskDto.getDescription());
        tasks.setStatus(taskDto.getStatus());
        Optional<Project> result = projectRepository.findById(taskDto.getProjectId());
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        tasks.setPerimeter(result.get());
        taskRepository.save(tasks);
        return ResponseEntity.ok().body((tasks));
    }

    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    @PutMapping("/api/v1/tasks/{id}")
    public @ResponseBody ResponseEntity<Task> modifytasks(@PathVariable int id,
            @RequestBody TaskRequest taskDto) {
        Optional<Task> result = taskRepository.findById(id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Task tasks = result.get();
        tasks.setTitle(taskDto.getTitle());
        tasks.setDescription(taskDto.getDescription());
        tasks.setStatus(taskDto.getStatus());
        Optional<Project> projectResult = projectRepository.findById(taskDto.getProjectId());
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        tasks.setPerimeter(projectResult.get());
        taskRepository.save(tasks);
        return ResponseEntity.ok().body((tasks));
    }
    
    @PreAuthorize("hasAnyRole('ROLE_DEVELOPER', 'ROLE_MANAGER', 'ROLE_ADMIN')")
    @DeleteMapping("/api/v1/tasks/{id}")
    public @ResponseBody ResponseEntity<Task> delete(@PathVariable int id) {
        Optional<Task> result = taskRepository.findById(id);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();
        taskRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /*
     * ================================================================================
     * GESTION DES ACTEURS D'UNE TACHE
     */

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @PostMapping("/api/v1/tasks/{id}/actors")
    public @ResponseBody ResponseEntity<Task> createtasksUser(@PathVariable int id,
            @RequestBody TaskAppUserRequest taskUserDto) {
        Optional<Task> resultTask = taskRepository.findById(id); 
        if (resultTask.isEmpty()) { 
            return ResponseEntity.notFound().build();
        }
        Task task = resultTask.get(); 
        Optional<AppUser> result = appUserRepository.findById(taskUserDto.getActorId()); 
                                                                                         
                                                                                         
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        task.getActors().add(result.get()); 
        taskRepository.save(task); 
        return ResponseEntity.ok().body((task)); 
    }

    @PreAuthorize("hasAnyRole('ROLE_MANAGER', 'ROLE_ADMIN')")
    @DeleteMapping("/api/v1/tasks/{id}/actors/{userId}")
    public @ResponseBody ResponseEntity<Task> deleteTaskUser(@PathVariable int id, @PathVariable int userId) {
        Optional<Task> result = taskRepository.findById(id); 
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        Task task = result.get(); 
        Optional<AppUser> resultAppUser = appUserRepository.findById(userId); 
                                                                              
        task.getActors().remove(resultAppUser.get()); 
        taskRepository.save(task);
        return ResponseEntity.ok().build();
    }
    
}

package com.taskdash.taskdash.repository;

import com.taskdash.taskdash.domain.Project;

import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Integer> {
    
}

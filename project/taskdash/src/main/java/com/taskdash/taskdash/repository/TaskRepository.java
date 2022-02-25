package com.taskdash.taskdash.repository;

import com.taskdash.taskdash.domain.Task;

import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Integer> {

}

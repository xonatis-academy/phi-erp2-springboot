package com.taskdash.taskdash.repository;

import org.springframework.data.repository.CrudRepository;

import com.taskdash.taskdash.domain.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {

}

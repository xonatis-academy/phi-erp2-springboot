package com.taskdash.taskdash.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.taskdash.taskdash.domain.AppUser;

public interface AppUserRepository extends CrudRepository<AppUser, Integer> {
	Optional<AppUser> findByUsername(String username);
}

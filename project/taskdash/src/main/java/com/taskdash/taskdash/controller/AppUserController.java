package com.taskdash.taskdash.controller;

import java.util.Optional;

import com.taskdash.taskdash.domain.AppUser;
import com.taskdash.taskdash.domain.Role;
import com.taskdash.taskdash.dto.AppUserRequest;
import com.taskdash.taskdash.dto.AppUserRoleRequest;
import com.taskdash.taskdash.repository.AppUserRepository;
import com.taskdash.taskdash.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppUserController {

    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired 
    private RoleRepository roleRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/api/v1/users")
    public @ResponseBody ResponseEntity<Iterable<AppUser>> find() {
        Iterable<AppUser> users = appUserRepository.findAll();
        return ResponseEntity.ok().body(users);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/api/v1/users/{id}")
    public @ResponseBody ResponseEntity<AppUser> getid(@PathVariable int id) {
        Optional<AppUser> result = appUserRepository.findById(id);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();
        AppUser appuser = result.get();
        return ResponseEntity.ok().body(appuser);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/api/v1/users")
    public @ResponseBody ResponseEntity<AppUser> createproject(@RequestBody AppUserRequest requestDto) {
        AppUser appUser = new AppUser();
        appUser.setUsername(requestDto.getUsername());
        appUser.setPassword(requestDto.getPassword());
        appUserRepository.save(appUser);
        return ResponseEntity.ok().body((appUser));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/api/v1/users/{id}")
    public @ResponseBody ResponseEntity<AppUser> modifyProject(@PathVariable int id,
            @RequestBody AppUserRequest requestDto) {
        Optional<AppUser> result = appUserRepository.findById(id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        AppUser appUser = result.get();
        appUser.setUsername(requestDto.getUsername());
        appUser.setPassword(requestDto.getPassword());
        appUserRepository.save(appUser);
        return ResponseEntity.ok().body((appUser));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/api/v1/users/{id}")
    public @ResponseBody ResponseEntity<AppUser> delete(@PathVariable int id) {
        Optional<AppUser> result = appUserRepository.findById(id);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();
            appUserRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/api/v1/users/{id}/roles")
    public @ResponseBody ResponseEntity<AppUser> createtasksUser(@PathVariable int id,
            @RequestBody AppUserRoleRequest userRoleDto) {
        Optional<AppUser> resultUser = appUserRepository.findById(id); 
        if (resultUser.isEmpty()) { 
            return ResponseEntity.notFound().build();
        }
        AppUser user = resultUser.get(); 
        Optional<Role> result = roleRepository.findById(userRoleDto.getRoleId());                                                         
                                                                                         
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        user.getRoles().add(result.get()); 
        appUserRepository.save(user); 
        return ResponseEntity.ok().body((user)); 
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/api/v1/users/{id}/roles/{roleId}")
    public @ResponseBody ResponseEntity<AppUser> deleteTaskUser(@PathVariable int id, @PathVariable int roleId) {
        Optional<AppUser> result = appUserRepository.findById(id); 
        if (result.isEmpty())
            return ResponseEntity.notFound().build();

        AppUser user = result.get(); 
        Optional<Role> resultRole = roleRepository.findById(roleId); 
                                                                              
        user.getRoles().remove(resultRole.get()); 
        appUserRepository.save(user);
        return ResponseEntity.ok().build();
    }
    
}

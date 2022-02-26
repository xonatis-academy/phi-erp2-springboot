package com.taskdash.taskdash.controller;

import java.util.Optional;

import com.taskdash.taskdash.domain.Role;
import com.taskdash.taskdash.dto.RoleRequest;
import com.taskdash.taskdash.repository.RoleRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/api/v1/roles")
    public @ResponseBody ResponseEntity<Iterable<Role>> find() {
        Iterable<Role> roles = roleRepository.findAll();
        return ResponseEntity.ok().body(roles);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/api/v1/roles/{id}")
    public @ResponseBody ResponseEntity<Role> getid(@PathVariable int id) {
        Optional<Role> result = roleRepository.findById(id);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();
        Role role = result.get();
        return ResponseEntity.ok().body(role);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/api/v1/roles")
    public @ResponseBody ResponseEntity<Role> createproject(@RequestBody RoleRequest requestDto) {
        Role role = new Role();
        role.setName(requestDto.getName());
        roleRepository.save(role);
        return ResponseEntity.ok().body((role));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/api/v1/roles/{id}")
    public @ResponseBody ResponseEntity<Role> modifyProject(@PathVariable int id,
            @RequestBody RoleRequest requestDto) {
        Optional<Role> result = roleRepository.findById(id);
        if (result.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Role role = result.get();
        role.setName(requestDto.getName());
        roleRepository.save(role);
        return ResponseEntity.ok().body((role));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/api/v1/roles/{id}")
    public @ResponseBody ResponseEntity<Role> delete(@PathVariable int id) {
        Optional<Role> result = roleRepository.findById(id);
        if (result.isEmpty())
            return ResponseEntity.notFound().build();
        roleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

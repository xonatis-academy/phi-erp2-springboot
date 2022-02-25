package com.taskdash.taskdash.domain;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String description;
    private int status;

    @ManyToOne
    private Project perimeter;

    @ManyToMany
    private Collection<AppUser> actors;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Project getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(Project perimeter) {
        this.perimeter = perimeter;
    }

    public Collection<AppUser> getActors() {
        return actors;
    }

    public void setActors(Collection<AppUser> actors) {
        this.actors = actors;
    }
}

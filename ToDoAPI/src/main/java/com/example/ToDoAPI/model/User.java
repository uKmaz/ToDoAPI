package com.example.ToDoAPI.model;

import java.util.List;

public class User {
    private Long id;
    private String name;
    private Role role; // Using enum for role
    private List<Task> tasks;
    private Company company;

    public User() {}

    public User(Long id, String name, Role role, List<Task> tasks, Company company) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.company = company;
        this.tasks = tasks;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public List<Task> getTasks() { return tasks; }
    public void addTask(Task task) { tasks.add(task); }
    public void deleteTask(Task task) { tasks.remove(task); }

    public Company getCompany() { return company; }
    public void setCompany(Company company) { this.company = company; }
}



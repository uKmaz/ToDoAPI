package com.example.ToDoAPI.model;

public class Task {
    private Company company;
    private Long id;
    private String title;
    private String description;
    private boolean completed;

    public Task() {}

    public Task(Company company, Long id,String title, String description, boolean completed) {
    	this.company=company;
        this.id = id;
        this.title=title;
        this.description = description;
        this.completed = completed;
    }
    public Company getCompany() {return company;}
    public void setCompany(Company company) {this.company=company;}
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

}
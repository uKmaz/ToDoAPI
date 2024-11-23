package com.example.ToDoAPI.service;

import com.example.ToDoAPI.model.Company;
import com.example.ToDoAPI.model.Task;

import org.springframework.stereotype.Service;  // Import this annotation

import java.util.List;

@Service  // Add this annotation
public class TaskServices {
    private List<Task> tasks;
    
    public TaskServices(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTaskById(Long id) {
        return tasks.stream()
                    .filter(task -> task.getId().equals(id))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Task not found with id: " + id)); // Handle not found
    }


    public List<Task> getAllTasks() {
        return tasks;
    }

    public void createTask(Task task, Company company) {
        tasks.add(task);
        company.addTask(task);
    }

    public void updateTask(Long taskId, Task task) {
        Task existingTask = tasks.stream()
                                 .filter(t -> t.getId().equals(taskId))
                                 .findFirst()
                                 .orElse(null);
        if (existingTask != null) {
            existingTask.setId(task.getId());
            existingTask.setDescription(task.getDescription());
            existingTask.setCompleted(task.isCompleted());
        }
    }

    public void deleteTask(Long taskId) {
        tasks.removeIf(task -> task.getId().equals(taskId));
    }
    

}

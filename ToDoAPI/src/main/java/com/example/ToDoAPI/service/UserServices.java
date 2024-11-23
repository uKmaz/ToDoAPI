package com.example.ToDoAPI.service;

import com.example.ToDoAPI.model.Company;
import com.example.ToDoAPI.model.Role;
import com.example.ToDoAPI.model.Task;
import com.example.ToDoAPI.model.User;
import org.springframework.stereotype.Service; // Import this annotation

import java.util.List;
import java.util.stream.Collectors;


@Service  // Add this annotation
public class UserServices {
    private List<User> users;

    public UserServices(List<User> users) {
        this.users = users;
    }

    public User getUserById(Long userId) {
        return users.stream()
                    .filter(user -> user.getId().equals(userId))
                    .findFirst()
                    .orElse(null);
    }

    public List<User> getUsersByCompany(Long companyId) {
        return users.stream()
                    .filter(user -> user.getCompany().getId().equals(companyId))
                    .collect(Collectors.toList());
    }

    public List<User> getAllUsers() {
        return users;
    }
    public User addUser(User user) {
    	users.add(user);    
    	return user;
    }
    public boolean deleteUserById(Long userId) {
        return users.removeIf(user -> user.getId().equals(userId));
    }
    public User addTaskById(User user,Task task) {
    	user.addTask(task);
    	return user;
    }
    public User updateID(User user,Long id) {
    	user.setId(id);
    	return user;
    }
    public User updateName(User user,String name) {
    	user.setName(name);
    	return user;
    }
    public User updateCompany(User user,Company company) {
    	user.setCompany(company);
    	return user;
    }
    public User updateRole(User user, Role role) {
    	user.setRole(role);
    	return user;
    }
}

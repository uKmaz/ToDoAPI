package com.example.ToDoAPI.controller;

import com.example.ToDoAPI.model.Company;
import com.example.ToDoAPI.model.Role;
import com.example.ToDoAPI.model.Task;
import com.example.ToDoAPI.model.User;
import com.example.ToDoAPI.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{UserID}/users")
public class UserController {

    private final UserServices userService;

    @Autowired  // Constructor injection
    public UserController(UserServices userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
        boolean isDeleted = userService.deleteUserById(id);
        return isDeleted ? "User deleted successfully" : "User not found";
    }

    @GetMapping("/company/{companyId}")
    public List<User> getUsersByCompany(@PathVariable Long companyId) {
        return userService.getUsersByCompany(companyId);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @PutMapping("/{id}/name")
    public User updateUserName(@PathVariable Long id, @RequestParam String name) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return userService.updateName(user, name);
    }
    @PostMapping("/{id}/tasks")
    public User addTaskById(@PathVariable Long id, @RequestBody Task task) {
        User user = userService.getUserById(id);
        if (user != null) {
            user.addTask(task);
            return userService.addUser(user);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }


    @PutMapping("/{id}/role")
    public User updateUserRole(@PathVariable Long id, @RequestParam Role role) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return userService.updateRole(user, role);
    }

    @PutMapping("/{id}/company")
    public User updateUserCompany(@PathVariable Long id, @RequestBody Company company) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return userService.updateCompany(user, company);
    }

    @PutMapping("/{id}/id")
    public User updateUserId(@PathVariable Long id, @RequestParam Long newId) {
        User user = userService.getUserById(id);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }
        return userService.updateID(user, newId);
    }
}

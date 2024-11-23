	package com.example.ToDoAPI.controller;

import com.example.ToDoAPI.model.Company;
import com.example.ToDoAPI.model.Role;
import com.example.ToDoAPI.model.Task;
import com.example.ToDoAPI.model.User;
import com.example.ToDoAPI.service.CompanyServices;
import com.example.ToDoAPI.service.TaskServices;
import com.example.ToDoAPI.service.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/{UserID}/tasks")
public class TaskController {

    private final TaskServices taskService;
    private final UserServices userService;
    private final CompanyServices companyService;
    
    @Autowired
    public TaskController(TaskServices taskService, UserServices userService, CompanyServices companyService) {
        this.taskService = taskService;
        this.userService = userService;
        this.companyService = companyService;
    }

    @GetMapping
    public List<Task> getAllTasks(@PathVariable Long UserID) {
    	User tempUser = userService.getUserById(UserID);
		List<Task> tasks=taskService.getAllTasks();

    	if(tempUser.getRole()==Role.STANDARD_USER) {
    		 return null;
    	}
    	else if(tempUser.getRole()==Role.COMPANY_ADMIN) {
    		List<Task> tempTasks = null;
    		for(int i =0; i < tasks.size(); i++) {
    			if(tasks.get(i).getCompany()==tempUser.getCompany()) {
    				tempTasks.add(tasks.get(i));
    			}
    		}
    		return tempTasks;
    		
    	}
    	else if(tempUser.getRole()==Role.SUPER_USER) {
    		return tasks;
    	}
    	else
    		return null;
       
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long UserID,@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        User tempUser=userService.getUserById(UserID);
        if(tempUser.getRole()==Role.STANDARD_USER) {
        	return null;
        }
        else if(tempUser.getRole()==Role.COMPANY_ADMIN) {
        	if(task.getCompany()==tempUser.getCompany()) {
        		return task;
        	}
        	else
        		return null;
        }
        else if(tempUser.getRole()==Role.SUPER_USER) {
        	return task;
        }
        else {
        	return null;
        }
        	

    }

    @PostMapping("/{companyId}")
    public Task createTask(@PathVariable Long UserID, @RequestBody Task task, @PathVariable Long companyId) {
        User tempUser = userService.getUserById(UserID);
        Company tempCompany = companyService.getCompanyById(companyId);
        taskService.createTask(task,tempCompany);
        return task;
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id, @PathVariable Long UserID) {
        User tempUser = userService.getUserById(UserID);
        Task tempTask = taskService.getTaskById(id);
        if(tempUser.getRole()==Role.STANDARD_USER) {
        	if(tempUser.getTasks().contains(tempTask) ){
                taskService.deleteTask(id);
        	}
        }
        else if(tempUser.getRole()==Role.COMPANY_ADMIN) {
        	if(tempTask.getCompany()==tempUser.getCompany())
                taskService.deleteTask(id);
        }
        else if(tempUser.getRole()==Role.SUPER_USER) {
            taskService.deleteTask(id);
        }

        
    }
    
    @PutMapping("/{id}")
    public void UpdateTask(@PathVariable Long id, @RequestBody Task task,@PathVariable Long UserID) {
    	User tempUser = userService.getUserById(UserID);
        Task tempTask = taskService.getTaskById(id);
        if(tempUser.getRole()==Role.STANDARD_USER) {
        	if(tempUser.getTasks().contains(tempTask) ){
                taskService.updateTask(id,task);
        	}
        }
        else if(tempUser.getRole()==Role.COMPANY_ADMIN) {
        	if(tempTask.getCompany()==tempUser.getCompany())
                taskService.updateTask(id,task);
        }
        else if(tempUser.getRole()==Role.SUPER_USER) {
            taskService.updateTask(id,task);
        }
    }

}

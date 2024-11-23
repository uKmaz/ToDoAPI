package com.example.ToDoAPI.controller;

import com.example.ToDoAPI.model.Company;
import com.example.ToDoAPI.model.Role;
import com.example.ToDoAPI.model.User;
import com.example.ToDoAPI.service.CompanyServices;
import com.example.ToDoAPI.service.UserServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/{UserID}/companies")
public class CompanyController {

    @Autowired
    private CompanyServices companyService;
    @Autowired
    private UserServices userService;
    
    // Get all companies
    @GetMapping
    public List<Company> getAllCompanies(@PathVariable Long UserID) {
    	User tempUser=userService.getUserById(UserID);
    	
        if(tempUser.getRole()==Role.STANDARD_USER) {
        	return (List) tempUser.getCompany();
        }
        else if(tempUser.getRole()==Role.COMPANY_ADMIN) {
            return companyService.getAllCompanies();
        }
        else if(tempUser.getRole()==Role.SUPER_USER) {
            return companyService.getAllCompanies();
        }
        else {
        	return null;
        }
    }

    // Get a company by ID
    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long UserID, @PathVariable Long id) {
        Company company = companyService.getCompanyById(id);
        User tempUser=userService.getUserById(UserID);
    	
        if(tempUser.getRole()==Role.STANDARD_USER) {
        	return null;
        }
        else if(tempUser.getRole()==Role.COMPANY_ADMIN) {
            return company;
        }
        else if(tempUser.getRole()==Role.SUPER_USER) {
            return company;
        }
        else {
        	return null;
        }
    }

    // Create a new company
    @PostMapping
    public Company createCompany(@PathVariable Long UserID,@RequestBody Company company) {
    	List<Company>companies=companyService.getAllCompanies();
    	boolean doesExist=false;

    	for(int i =0;i<companies.size();i++) {
    		if(companies.get(i).getId()==company.getId())
    			doesExist=true;
    	}
    	if(!doesExist) {
    		User tempUser=userService.getUserById(UserID);
            if(tempUser.getRole()!=Role.STANDARD_USER) {
            	companyService.createCompany(company);
                return company;
            }
            return null;
    	}
        	return null;
    }

    // Update an existing company
    @PutMapping("/{id}")
    public Company updateCompany(@PathVariable Long UserID, @PathVariable Long id, @RequestBody Company company) {
        User tempUser=userService.getUserById(UserID);
        Company tempCompany = companyService.getCompanyById(id);
        if(tempUser.getRole()==Role.COMPANY_ADMIN) {
        	if(tempUser.getCompany()==tempCompany) {
        		companyService.updateCompany(id, company);
        		return company;
        	}
        }
        else if(tempUser.getRole()==Role.SUPER_USER) {
        	companyService.updateCompany(id, company);
    		return company;
        }
        
        	return null;
    }

    // Delete a company by ID
    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable Long UserID,@PathVariable Long id) {
        companyService.deleteCompany(id);
        User tempUser=userService.getUserById(UserID);
        Company tempCompany = companyService.getCompanyById(id);
        if(tempUser.getRole()==Role.COMPANY_ADMIN) {
        	if(tempUser.getCompany()==tempCompany) {
                companyService.deleteCompany(id);

        	}
        }
        else if(tempUser.getRole()==Role.SUPER_USER) {
            companyService.deleteCompany(id);
        }
    }
    

}

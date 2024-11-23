package com.example.ToDoAPI.service;

import com.example.ToDoAPI.model.Company;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServices {

    private List<Company> companies; 

    public CompanyServices(List<Company> companies) {
        this.companies = companies;
    }

    // Get all companies
    public List<Company> getAllCompanies() {
        return companies;
    }

    // Get a company by ID
    public Company getCompanyById(Long id) {
        for (Company company : companies) {
            if (company.getId().equals(id)) {
                return company;
            }
        }
        return null;
    }


    // Create a new company
    public void createCompany(Company company) {
        companies.add(company);
    }

    // Update a company
    public Company updateCompany(Long id, Company company) {
        for(int i =0;i<companies.size();i++)
        {
        	if(companies.get(i).getId()==id) {
        		companies.set(i, company);
                return company;
        	}
        	
        }
        return null;

    }

    public void deleteCompany(Long id) {
        Company company = getCompanyById(id);
        int temp = companies.indexOf(company);
        companies.remove(temp);
    }
}

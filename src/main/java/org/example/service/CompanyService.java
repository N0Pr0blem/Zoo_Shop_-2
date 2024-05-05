package org.example.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.model.Company;
import org.example.repos.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepo;
    Logger logger = LogManager.getLogger(CompanyService.class);

    public ArrayList<Company> getAll() {
        return (ArrayList<Company>) companyRepo.findAll();
    }

    public void addCompany(Company company) {
        Company companyFromDB = companyRepo.findByName(company.getName());
        if(companyFromDB==null){
            companyRepo.save(company);
        }
    }

    public void delete(Company company) {
        companyRepo.delete(company);
    }

    public void edit(Company company, String name) {
        company.setName(name);
        companyRepo.save(company);
    }
}

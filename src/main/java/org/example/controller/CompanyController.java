package org.example.controller;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.example.model.Company;
import org.example.repos.CompanyRepository;
import org.example.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping()
    public String allCompanies(Model model){
        model.addAttribute("companies",companyService.getAll());
        return "companies";
    }

}

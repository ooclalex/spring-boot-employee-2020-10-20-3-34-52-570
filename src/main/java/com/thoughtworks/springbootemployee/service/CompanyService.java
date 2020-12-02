package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Company;
import com.thoughtworks.springbootemployee.Employee;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }


    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Employee create(Company companyRequest) {
        return null;
    }
}

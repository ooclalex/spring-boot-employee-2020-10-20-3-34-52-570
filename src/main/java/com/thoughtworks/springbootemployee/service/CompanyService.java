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

    public Company create(Company companyRequest) {
        return companyRepository.create(companyRequest);
    }

    public Company update(int companyId, Company companyRequest) {
        return companyRepository.update(companyId, companyRequest);
    }

    public void delete(int companyId) {
        companyRepository.delete(companyId);
    }

    public Company getSpecificCompany(Integer companyId) {
        return companyRepository.findSpecificCompany(companyId);
    }

    public List<Company> getAllByPaging(Integer page, Integer pageSize) {
        return companyRepository.findAllByPaging(page, pageSize);
    }

    public List<Employee> getEmployeesUnderCompany(int companyId) {
        return companyRepository.getEmployeesUnderCompany(companyId);
    }
}

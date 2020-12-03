package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Company;
import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;


    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company create(Company companyRequest) {
        return companyRepository.save(companyRequest);
    }

    public Company update(String companyId, Company companyRequest) {
        if(getSpecificCompany(companyId) != null) {
            companyRequest.setId(companyId);
            return companyRepository.save(companyRequest);
        }
        return null;
    }

    public void delete(String companyId) {
        companyRepository.deleteById(companyId);
    }

    public Company getSpecificCompany(String companyId) {
        return companyRepository.findById(companyId).orElseThrow(NotFoundException::new);
    }

    public List<Company> getAllByPaging(Integer page, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(page, pageSize)).getContent();
    }

    public List<Employee> getEmployeesUnderCompany(String companyId) {
        return this.getSpecificCompany(companyId).getEmployees();
    }
}

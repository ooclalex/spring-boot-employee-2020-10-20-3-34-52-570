package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.Company;
import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompanyRepository {
    private final List<Company> companies = new ArrayList<>();

    public CompanyRepository() {

    }

    public List<Company> findAll() {
        return this.companies;
    }

    public Company create(Company companyRequest) {
        companies.add(companyRequest);
        return companyRequest;
    }

    public Company update(Integer companyId, Company companyRequest) {
        companies.stream().filter(employee -> companyId.equals(employee.getCompanyId())).findFirst().ifPresent(
                employee -> {
                    companies.remove(employee);
                    companies.add(companyRequest);
                }
        );
        return companyRequest;
    }

    public void delete(Integer companyId) {
        companies.stream().filter(company -> companyId.equals(company.getCompanyId())).findFirst().ifPresent(companies::remove);
    }

    public Company findSpecificCompany(Integer companyId) {
        return companies.stream()
                .filter(employee -> companyId.equals(employee.getCompanyId()))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public List<Company> findAllByPaging(Integer page, Integer pageSize) {
        return companies.stream().skip(pageSize * page).limit(pageSize).collect(Collectors.toList());
    }

    public List<Employee> getEmployeesUnderCompany(int companyId) {
        return findSpecificCompany(companyId).getEmployees();
    }
}

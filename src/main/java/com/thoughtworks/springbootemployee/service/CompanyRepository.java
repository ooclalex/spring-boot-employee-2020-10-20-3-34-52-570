package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Company;

import java.util.ArrayList;
import java.util.List;

public class CompanyRepository {
    private final List<Company> companies = new ArrayList<>();
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
        return null;
    }
}

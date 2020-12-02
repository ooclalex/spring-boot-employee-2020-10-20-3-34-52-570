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
}

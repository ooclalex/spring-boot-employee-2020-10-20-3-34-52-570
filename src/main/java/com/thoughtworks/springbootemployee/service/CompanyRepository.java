package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Company;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

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
        return null;
    }
}

package com.thoughtworks.springbootemployee;

import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

public class Company {
    @MongoId(FieldType.OBJECT_ID)
    private Integer companyId;
    private String companyName;
    private Integer employeesNumber;
    private List<Employee> employees;

    public Company() {

    }

    public Company(Integer companyId, String companyName, Integer employeesNumber, List<Employee> employees) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
    }

    public Integer getCompanyId() {
        return this.companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}

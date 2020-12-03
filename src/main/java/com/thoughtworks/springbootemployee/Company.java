package com.thoughtworks.springbootemployee;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class Company {
    @MongoId(FieldType.OBJECT_ID)
    private String companyId;
    private String companyName;
    private Integer employeesNumber;
    private List<Employee> employees;

    public Company() {

    }

    public Company(String companyName, Integer employeesNumber, List<Employee> employees) {
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
    }

    public String getCompanyId() {
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

    public void setId(String companyId) {
        this.companyId = companyId;
    }
}

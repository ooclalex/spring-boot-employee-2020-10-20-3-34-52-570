package com.thoughtworks.springbootemployee;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document
public class Company {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String companyName;
    private Integer employeesNumber;
    //todo list of ids
    private List<String> employees;

    public Company() {

    }

    public Company(String companyName, Integer employeesNumber, List<String> employees) {
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
    }

    public String getId() {
        return this.id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getEmployeesNumber() {
        return employeesNumber;
    }

    public List<String> getEmployees() {
        return employees;
    }

    public void setId(String companyId) {
        this.id = companyId;
    }
}

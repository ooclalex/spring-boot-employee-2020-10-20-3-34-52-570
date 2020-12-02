package com.thoughtworks.springbootemployee;

import java.util.List;

public class Company {
    private String companyName;
    private int employeesNumber;
    private List<Employee> employees;

    public Company() {

    }

    public Company(String companyName, int employeesNumber, List<Employee> employees) {
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
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

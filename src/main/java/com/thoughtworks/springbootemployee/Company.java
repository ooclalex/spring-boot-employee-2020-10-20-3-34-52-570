package com.thoughtworks.springbootemployee;

import java.util.List;

public class Company {
    private int companyId;
    private String companyName;
    private int employeesNumber;
    private List<Employee> employees;

    public Company() {

    }

    public Company(int companyId, String companyName, int employeesNumber, List<Employee> employees) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.employeesNumber = employeesNumber;
        this.employees = employees;
    }

    public int getCompanyId() {
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

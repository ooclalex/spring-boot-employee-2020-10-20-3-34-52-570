package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.Employee;

import java.util.List;

public class EmployeeRepository {
    List<Employee> employee;
    public List<Employee> findAll() {
        return employee;
    }
}

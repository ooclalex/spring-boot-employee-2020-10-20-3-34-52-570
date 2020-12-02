package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.Employee;

import java.util.List;

public class EmployeeRepository {
    List<Employee> employees;
    public List<Employee> findAll() {
        return employees;
    }

    public Employee create(Employee employeeRequest) {
        employees.add(employeeRequest);
        return employeeRequest;
    }

    public Employee update(Employee any) {
        return null;
    }
}

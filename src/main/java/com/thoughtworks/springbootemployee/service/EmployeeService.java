package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee create(Employee employeeRequest) {
        return employeeRepository.create(employeeRequest);
    }
}

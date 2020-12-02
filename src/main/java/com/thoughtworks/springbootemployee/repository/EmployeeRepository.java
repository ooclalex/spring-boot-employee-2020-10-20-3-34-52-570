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

    public Employee update(Integer id, Employee employeeRequest) {
        employees.stream().filter(employee -> id.equals(employee.getId())).findFirst().ifPresent(
                employee -> {
                    employees.remove(employee);
                    employees.add(employeeRequest);
                }
        );
        return employeeRequest;
    }

    public void delete(Integer id) {
    }
}

package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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
        employees.stream().filter(employee -> id.equals(employee.getId())).findFirst().ifPresent(
                employee -> employees.remove(employee)
        );
    }

    public List<Employee> findByGender(String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    public Employee findSpecficEmployee(Integer employeeId) {
        return employees.stream()
                .filter(employee -> employeeId.equals(employee.getId()))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    public List<Employee> findAllByPaging(Integer page, Integer pageSize) {
        return null;
    }
}

package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class EmployeeService {
    @Autowired
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee create(Employee employeeRequest) {
        return employeeRepository.create(employeeRequest);
    }

    public Employee update(Integer id, Employee employeeRequest) {
        return employeeRepository.update(id, employeeRequest);
    }

    public void delete(Integer id) {
        employeeRepository.delete(id);
    }

    public List<Employee> getByGender(String gender) {
        return employeeRepository.findByGender(gender);
    }

    public Employee getSpecificEmployee(Integer id) {
        return employeeRepository.findSpecificEmployee(id);
    }

    public List<Employee> getAllByPaging(Integer page, Integer pageSize) {
        return employeeRepository.findAllByPaging(page, pageSize);
    }
}

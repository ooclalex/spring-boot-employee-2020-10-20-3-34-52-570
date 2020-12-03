package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public Employee create(Employee employeeRequest) {
        return employeeRepository.save(employeeRequest);
    }

    public Employee update(String employeeId, Employee employeeRequest) {
        employeeRepository.deleteById(employeeId);
        return employeeRepository.save(employeeRequest);
    }

    public void delete(String employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    public List<Employee> getByGender(String gender) {
        return employeeRepository.findAllByGender(gender);
    }

    public Employee getSpecificEmployee(String employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(NotFoundException::new);
    }

    public Page<Employee> getAllByPaging(Integer page, Integer pageSize) {
        return employeeRepository.findAll(PageRequest.of(page, pageSize));
    }
}

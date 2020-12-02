package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    private final List<Employee> employees = new ArrayList<>();

    @GetMapping
    public List<Employee> getAll() {
        return employees;
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Employee> getAllByPaging(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize
    ) {
        return employees.stream().skip(pageSize * page).limit(pageSize).collect(Collectors.toList());
    }

    @GetMapping(params = {"gender"})
    public List<Employee> getAllByGender(@RequestParam(required = false) String gender) {
        return employees.stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }

    @GetMapping("/{employeeId}")
    public Employee getSpecificEmployee(@PathVariable Integer employeeId) {
        return employees.stream()
                .filter(employee -> employeeId.equals(employee.getEmployeeId()))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }


    @PostMapping
    public Employee create(@RequestBody Employee employeeUpdate) {
        employees.add(employeeUpdate);
        return employeeUpdate;
    }

    @PutMapping("/{employeeId}")
    public Employee update(@PathVariable Integer employeeId, @RequestBody Employee employeeUpdate) {
        employees.stream().filter(employee -> employeeId.equals(employee.getEmployeeId())).findFirst().ifPresent(
                employee -> {
                    employees.remove(employee);
                    employees.add(employeeUpdate);
                }
        );
        return employeeUpdate;
    }

    @DeleteMapping("/{employeeId}")
    public void delete(@PathVariable Integer employeeId) {
        employees.stream().filter(employee -> employeeId.equals(employee.getEmployeeId())).findFirst().ifPresent(employees::remove);
    }
}

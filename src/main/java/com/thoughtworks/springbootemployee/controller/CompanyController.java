package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Company;
import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;
    private final List<Company> companies = new ArrayList<>();

    @GetMapping
    public List<Company> getAll() {
        return companyService.getAll();
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getAllByPaging(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize
    ) {
        return companyService.getAllByPaging(page, pageSize);
    }

    @GetMapping("/{companyId}")
    public Company getSpecificCompany(@PathVariable String companyId) {
        return companyService.getSpecificCompany(companyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Company create(@RequestBody Company companyUpdate) {
        return companyService.create(companyUpdate);
    }

    @PutMapping("/{companyId}")
    public Company update(@PathVariable String companyId, @RequestBody Company companyUpdate) {
        return companyService.update(companyId, companyUpdate);
    }

    @DeleteMapping("/{companyId}")
    public void delete(@PathVariable String companyId) {
        companyService.delete(companyId);
    }

    @PostMapping("/{companyId}/employees")
    public List<Employee> getEmployeesUnderCompany(@PathVariable String companyId) {
        return companyService.getEmployeesUnderCompany(companyId);
    }
}
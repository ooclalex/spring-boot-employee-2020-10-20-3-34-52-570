package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.Company;
import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.exception.NotFoundException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final List<Company> companies = new ArrayList<>();

    @GetMapping
    public List<Company> getAll() {
        return companies;
    }

    @GetMapping(params = {"page", "pageSize"})
    public List<Company> getAllByPaging(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer pageSize
    ) {
        return companies.stream().skip(pageSize * page).limit(pageSize).collect(Collectors.toList());
    }

    @GetMapping("/{companyId}")
    public Company getSpecificCompany(@PathVariable Integer companyId) {
        return companies.stream()
                .filter(company -> companyId.equals(company.getCompanyId()))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @PostMapping
    public Company create(@RequestBody Company companyUpdate) {
        companies.add(companyUpdate);
        return companyUpdate;
    }

    @PutMapping("/{companyId}")
    public Company update(@PathVariable Integer companyId, @RequestBody Company companyUpdate) {
        companies.stream().filter(company -> companyId.equals(company.getCompanyId())).findFirst().ifPresent(
                company -> {
                    companies.remove(company);
                    companies.add(companyUpdate);
                }
        );
        return companyUpdate;
    }

    @DeleteMapping("/{companyId}")
    public void delete(@PathVariable Integer companyId) {
        companies.stream().filter(company -> companyId.equals(company.getCompanyId())).findFirst().ifPresent(companies::remove);
    }

    @PostMapping("/{companyId}/employees")
    public List<Employee> getEmployeesUnderCompany(@PathVariable String companyId) {
        CompanyService companyService = new CompanyService();
        return companyService.getEmployeesUnderCompany(companyId);
    }
}
package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.Company;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CompanyServiceTest {
    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    void should_return_all_companies_when_get_all_given_all_companies() {
        //given
        final List<Company> expected = Collections.singletonList(new Company("Apple", 1000, new ArrayList<>()));
        when(companyRepository.findAll()).thenReturn(expected);

        //when
        final List<Company> companies = companyService.getAll();

        //then
        assertEquals(expected, companies);
    }

}

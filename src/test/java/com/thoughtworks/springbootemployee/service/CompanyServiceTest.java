package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.Company;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
    @InjectMocks
    private CompanyService companyService;

    @Mock
    private CompanyRepository companyRepository;

    @Test
    void should_return_all_companies_when_get_all_given_all_companies() {
        //given
        final List<Company> expected = Collections.singletonList(new Company(1, "Apple", 1000, new ArrayList<>()));
        when(companyRepository.findAll()).thenReturn(expected);

        //when
        final List<Company> companies = companyService.getAll();

        //then
        assertEquals(expected, companies);
    }

    @Test
    void should_return_created_company_when_create_company_given_no_company_company_request() {
        //given
        final Company company = new Company(1, "Apple", 1000, new ArrayList<>());
        when(companyRepository.create(any())).thenReturn(company);

        //when
        companyService.create(company);
        final ArgumentCaptor<Company> companyArgumentCaptor = ArgumentCaptor.forClass(Company.class);
        verify(companyRepository, times(1)).create(companyArgumentCaptor.capture());

        //then
        final Company actual = companyService.create(company);
        assertEquals(1, actual.getCompanyId());
    }
}

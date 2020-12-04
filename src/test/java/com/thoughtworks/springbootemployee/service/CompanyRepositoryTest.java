package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyRepositoryTest {
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

    @Test
    void should_return_created_company_when_create_company_given_no_company_company_request() {
        //given
        final Company company = new Company("Apple", 1000, new ArrayList<>());
        when(companyRepository.save(any())).thenReturn(company);

        //when
        companyService.create(company);
        final ArgumentCaptor<Company> companyArgumentCaptor = ArgumentCaptor.forClass(Company.class);
        verify(companyRepository, times(1)).save(companyArgumentCaptor.capture());

        //then
        final Company actual = companyService.create(company);
        assertEquals("Apple", actual.getCompanyName());
    }

//    @Test
//    void should_return_updated_company_when_update_company_given_company_id() {
//        //given
//        final Company company = new Company("Apple", 1000, new ArrayList<>());
//        final Company updatedCompany = new Company("Orange", 1000, new ArrayList<>());
//        when(companyRepository.save(any(), any())).thenReturn(updatedCompany);
//
//        //when
//        companyService.update(company.getCompanyId(), company);
//        final ArgumentCaptor<Company> companyArgumentCaptor = ArgumentCaptor.forClass(Company.class);
//        final ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
//        verify(companyRepository, times(1)).update(integerArgumentCaptor.capture(), companyArgumentCaptor.capture());
//
//        //then
//        final Company actual = companyService.update(company.getCompanyId(), company);
//        assertEquals("Orange", actual.getCompanyName());
//    }
//
    @Test
    void should_not_return_when_delete_company_given_company_id() {
        //given
        final Company company = new Company("Apple", 1000, new ArrayList<>());

        //when
        companyService.delete(company.getId());

        //then
        verify(companyRepository, times(1)).deleteById(company.getId());
    }

    @Test
    void should_return_specific_companies_when_get_specific_company_given_all_companies_and_id() {
        //given
        final String id = "1";
        final Company expected = new Company("Apple", 1000, new ArrayList<>());

        when(companyRepository.findById(id)).thenReturn(Optional.of(expected));

        //when
        final Company actual = companyService.getSpecificCompany(id);

        //then
        assertEquals(expected, actual);
    }

//    @Test
//    void should_return_companies_when_get_all_by_paging_given_all_companies_page_and_page_size() {
//        //given
//        final Integer page = 1, pageSize = 1;
//        final List<Company> expected = Collections.singletonList(new Company("Apple", 1000, new ArrayList<>()));
//
//        when(companyRepository.findAll(PageRequest.of(page, pageSize)).thenReturn(expected);
//
//        //when
//        final List<Company> actual = companyService.getAllByPaging(page, pageSize);
//
//        //then
//        assertEquals(expected, actual);
//    }

}

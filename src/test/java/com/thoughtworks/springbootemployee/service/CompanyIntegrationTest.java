package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Company;
import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    CompanyRepository companyRepository;

    @AfterEach
    void tearDown() {
        companyRepository.deleteAll();
    }

    @Test
    public void should_return_all_companies_when_get_all_given_companies() throws Exception {
        //given
        Company company = new Company("Apple", 1000, new ArrayList<>());
        companyRepository.save(company);

        //when
        //then
        mockMvc.perform(get("/companies"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].companyName").value("Apple"))
                .andExpect(jsonPath("$[0].employeesNumber").value(1000))
                .andExpect(jsonPath("$[0].employees").value(new ArrayList<>()));
    }

    @Test
    public void should_return_company_when_create_company_given_company() throws Exception {
        //given
        String companyAsJson = "    {\n" +
                "        \"companyName\": \"Apple\",\n" +
                "        \"employeesNumber\": 1000,\n" +
                "        \"employees\": []\n" +
                "    }";

        //when
        //then
        mockMvc.perform(post("/companies")
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.companyName").value("Apple"))
                .andExpect(jsonPath("$.employeesNumber").value(1000))
                .andExpect(jsonPath("$.employees").value(new ArrayList<>()));
    }

    @Test
    public void should_return_updated_company_when_update_company_given_company() throws Exception {
        //given
        Company company = companyRepository.save(new Company("Apple", 1000, new ArrayList<>()));
        String companyToString = "    {\n" +
                "        \"companyName\": \"Apple\",\n" +
                "        \"employeesNumber\": 100,\n" +
                "        \"employees\": []\n" +
                "    }";

        //when
        //then
        mockMvc.perform(put("/companies/" + company.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(companyToString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.companyName").value("Apple"))
                .andExpect(jsonPath("$.employeesNumber").value(100))
                .andExpect(jsonPath("$.employees").value(new ArrayList<>()));
    }

    @Test
    public void should_not_return_when_delete_company_given_company_id() throws Exception {
        //given
        Company company = companyRepository.save(new Company("Apple", 1000, new ArrayList<>()));

        //when
        //then
        mockMvc.perform(delete("/companies/" + company.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_return_specific_companies_when_get_specific_company_given_all_companies_and_id() throws Exception{
        //given
        Company company = companyRepository.save(new Company("Apple", 1000, new ArrayList<>()));

        //when, then
        mockMvc.perform(get("/companies/" + company.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.companyName").value("Apple"))
                .andExpect(jsonPath("$.employeesNumber").value(1000))
                .andExpect(jsonPath("$.employees").value(new ArrayList<>()));
    }
}

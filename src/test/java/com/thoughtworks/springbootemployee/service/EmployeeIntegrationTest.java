package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAll();
    }

    @Test
    public void should_return_all_employees_when_get_all_given_employees() throws Exception {
        //given
        Employee employee = new Employee("Alex", 18, 100, "Male");
        employeeRepository.save(employee);

        //when
        //then
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].name").value("Alex"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].salary").value(100))
                .andExpect(jsonPath("$[0].gender").value("Male"));
    }

    @Test
    public void should_return_employee_when_create_employee_given_employee() throws Exception {
        //given
        String employeeAsJson = "{\n" +
                "    \"name\": \"Alex\",\n" +
                "    \"age\": 18,\n" +
                "    \"salary\": 100,\n" +
                "    \"gender\": \"Male\"\n" +
                "}";

        //when
        //then
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeAsJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("Alex"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.salary").value(100))
                .andExpect(jsonPath("$.gender").value("Male"));
    }

    @Test
    public void should_return_updated_employee_when_update_employee_given_employee() throws Exception {
        //given
        Employee employee = employeeRepository.save(new Employee("Alex", 18, 100, "Male"));
        String employeeToString = "{\n" +
                "    \"name\": \"Alex\",\n" +
                "    \"age\": \"18\",\n" +
                "    \"salary\": \"1000\",\n" +
                "    \"gender\": \"Male\"\n" +
                "}";

        //when
        //then
        mockMvc.perform(put("/employees/" + employee.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeToString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("Alex"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.salary").value(1000))
                .andExpect(jsonPath("$.gender").value("Male"));
    }

    @Test
    public void should_not_return_when_delete_employee_given_employee_id() throws Exception {
        //given
        Employee employee = employeeRepository.save(new Employee("Alex", 18, 100, "Male"));

        //when
        //then
        mockMvc.perform(delete("/employees/" + employee.getId()))
                .andExpect(status().isNoContent());
    }

    @Test
    void should_return_Male_employees_when_get_all_by_gender_given_all_employees_and_gender() throws Exception{
        //given
        final String gender = "Male";
        Employee employee1 = employeeRepository.save(new Employee("Alex", 18, 1000, "Male"));
        Employee employee2 = employeeRepository.save(new Employee("Alice", 18, 1000, "Female"));

        //when
        //then
        mockMvc.perform(get("/employees?gender=" + gender))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].name").value("Alex"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].salary").value(1000))
                .andExpect(jsonPath("$[0].gender").value("Male"));
    }

    @Test
    void should_return_specific_employees_when_get_specific_employee_given_all_employees_and_id() throws Exception{
        //given
        Employee employee1 = employeeRepository.save(new Employee("Alex", 18, 1000, "Male"));

        //when, then
        mockMvc.perform(get("/employees/" + employee1.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").isString())
                .andExpect(jsonPath("$.name").value("Alex"))
                .andExpect(jsonPath("$.age").value(18))
                .andExpect(jsonPath("$.salary").value(1000))
                .andExpect(jsonPath("$.gender").value("Male"));
    }

    @Test
    void should_return_employees_when_get_all_by_paging_given_all_employees_page_and_page_size() throws Exception{
        //given
        final int page = 0, pageSize = 1;
        Employee employee1 = employeeRepository.save(new Employee("Alex", 18, 1000, "Male"));
        Employee employee2 = employeeRepository.save(new Employee("Alice", 18, 1000, "Female"));

        //when
        //then
        mockMvc.perform(get("/employees?page=" + page + "&pageSize=" + pageSize))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].name").value("Alex"))
                .andExpect(jsonPath("$[0].age").value(18))
                .andExpect(jsonPath("$[0].salary").value(1000))
                .andExpect(jsonPath("$[0].gender").value("Male"));
    }
}

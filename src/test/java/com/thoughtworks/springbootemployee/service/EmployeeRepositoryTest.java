package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeRepositoryTest {
    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void should_return_all_employees_when_get_all_given_all_employees() {
        //given
        final List<Employee> expected = Collections.singletonList(new Employee("test", 18, 1000, "male"));
        when(employeeRepository.findAll()).thenReturn(expected);

        //when
        final List<Employee> employees = employeeService.getAll();

        //then
        assertEquals(expected, employees);
    }

    @Test
    void should_return_created_employee_when_create_employee_given_no_employee_employee_request() {
        //given
        final Employee employee = new Employee("test", 18, 1000, "male");
        when(employeeRepository.save(any())).thenReturn(employee);
        employeeService.create(employee);

        //when
        final ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository, times(1)).save(employeeArgumentCaptor.capture());

        //then
        final Employee actual = employeeService.create(employee);
        assertEquals(18, actual.getAge());
    }

//    @Test
//    void should_return_updated_employee_when_update_employee_given_employee_id() {
//        //given
//        final Employee employee = new Employee("1", "test", 18, 1000, "male");
//        final Employee updatedEmployee = new Employee("1", "test", 18, 999, "male");
//        when(employeeRepository.findAllById(any()).save()).thenReturn(updatedEmployee);
//
//        //when
//        employeeService.update(employee.getEmployeeId(), employee);
//        final ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
//        final ArgumentCaptor<String> stringArgumentCaptor = ArgumentCaptor.forClass(String.class);
//        verify(employeeRepository, times(1)).findAllById(stringArgumentCaptor.capture()).save();
//
//        //then
//        final Employee actual = employeeService.update(employee.getEmployeeId(), employee);
//        assertEquals(999, actual.getSalary());
//    }

    @Test
    void should_not_return_when_delete_employee_given_employee_id() {
        //given
        final Employee employee = new Employee("test", 18, 1000, "male");

        //when
        employeeService.delete(employee.getId());

        //then
        verify(employeeRepository, times(1)).deleteById(employee.getId());
    }

    @Test
    void should_return_male_employees_when_get_all_by_gender_given_all_employees_and_gender() {
        //given
        final String gender = "male";
        Employee employee1 = new Employee("Alex", 18, 1000, "male");
        Employee employee2 = new Employee("Alice", 18, 1000, "female");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        List<Employee> expected = new ArrayList<>();
        expected.add(employee1);

        when(employeeRepository.findAllByGender(any())).thenReturn(expected);

        //when
        final List<Employee> actual = employeeService.getByGender(gender);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_specific_employees_when_get_specific_employee_given_all_employees_and_id() {
        //given
        final String id = "1";
        final Employee expected = new Employee("test", 18, 1000, "male");

        when(employeeRepository.findById(any())).thenReturn(Optional.of(expected));

        //when
        final Employee actual = employeeService.getSpecificEmployee(id);

        //then
        assertEquals(expected, actual);
    }

//    @Test
//    void should_return_employees_when_get_all_by_paging_given_all_employees_page_and_page_size() {
//        //given
//        final int page = 1, pageSize = 1;
//        List<Employee> expected = (Collections.singletonList(new Employee("test", 18, 1000, "male")));
//
//        when(employeeRepository.findAll(PageRequest.of(page, pageSize))).thenReturn(expected);
//
//        //when
//        final List<Employee> actual = employeeService.getAllByPaging(page, pageSize);
//
//        //then
//        assertEquals(expected, actual);
//    }
}

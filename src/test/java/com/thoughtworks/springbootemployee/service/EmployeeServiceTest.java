package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.Employee;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {
    @InjectMocks
    private EmployeeService employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @Test
    void should_return_all_employees_when_get_all_given_all_employees() {
        //given
        final List<Employee> expected = Collections.singletonList(new Employee(1, "test", 18, 1000, "male"));
        when(employeeRepository.findAll()).thenReturn(expected);

        //when
        final List<Employee> employees = employeeService.getAll();

        //then
        assertEquals(expected, employees);
    }

    @Test
    void should_return_created_employee_when_create_employee_given_no_employee_employee_request() {
        //given
        final Employee employee = new Employee(1, "test", 18, 1000, "male");
        when(employeeRepository.create(any())).thenReturn(employee);

        //when
        employeeService.create(employee);
        final ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(employeeRepository, times(1)).create(employeeArgumentCaptor.capture());

        //then
        final Employee actual = employeeService.create(employee);
        assertEquals(18, actual.getAge());
    }

    @Test
    void should_return_updated_employee_when_update_employee_given_employee_id() {
        //given
        final Employee employee = new Employee(1, "test", 18, 1000, "male");
        final Employee updatedEmployee = new Employee(1, "test", 18, 999, "male");
        when(employeeRepository.update(any(), any())).thenReturn(updatedEmployee);

        //when
        employeeService.update(employee.getId(), employee);
        final ArgumentCaptor<Employee> employeeArgumentCaptor = ArgumentCaptor.forClass(Employee.class);
        final ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(employeeRepository, times(1)).update(integerArgumentCaptor.capture(), employeeArgumentCaptor.capture());

        //then
        final Employee actual = employeeService.update(employee.getId(), employee);
        assertEquals(999, actual.getSalary());
    }

    @Test
    void should_not_return_null_when_delete_employee_given_employee_id() {
        //given
        final Employee employee = new Employee(1, "test", 18, 1000, "male");

        //when
        employeeService.delete(employee.getId());

        //then
        verify(employeeRepository, times(1)).delete(employee.getId());
    }

    @Test
    void should_return_male_employees_when_get_all_by_gender_given_all_employees_and_gender() {
        //given
        final String gender = "male";
        Employee employee1 = new Employee(1, "Alex", 18, 1000, "male");
        Employee employee2 = new Employee(2, "Alice", 18, 1000, "female");
        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        List<Employee> expected = new ArrayList<>();
        expected.add(employee1);

        when(employeeRepository.findByGender(any())).thenReturn(expected);

        //when
        final List<Employee> actual = employeeService.getByGender(gender);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_specific_employees_when_get_specific_employee_given_all_employees_and_id() {
        //given
        final Integer id = 1;
        final Employee expected = new Employee(1, "test", 18, 1000, "male");

        when(employeeRepository.findSpecficEmployee(any())).thenReturn(expected);

        //when
        final Employee actual = employeeService.getSpecificEmployee(id);

        //then
        assertEquals(expected, actual);
    }

    @Test
    void should_return_employees_when_get_all_by_paging_given_all_employees_page_and_page_size() {
        //given
        final Integer page = 1, pageSize = 1;
        final List<Employee> expected = Collections.singletonList(new Employee(1, "test", 18, 1000, "male"));

        when(employeeRepository.findAllByPaging(page, pageSize)).thenReturn(expected);

        //when
        final List<Employee> actual = employeeService.getAllByPaging(page, pageSize);

        //then
        assertEquals(expected, actual);
    }
}

package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class SpringBootEmployeeApplicationTests {

	@Test
	void should_return_all_employees_when_get_all_given_all_employees() {
		//given
		EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
		EmployeeService employeeService = new EmployeeService(employeeRepository);
		final List<Employee> expected = Collections.singletonList(new Employee(1, "test", 18, 1000, "male"));

		when(employeeRepository.findAll()).thenReturn(expected);

		//when
		final List<Employee> employees = employeeService.getAll();

		//then
		assertEquals(expected, employees);
	}

	@Test
	void should_return_created_employee_when_create_employee_given_no_employee_employee_request(){
		//given
		EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
		EmployeeService employeeService = new EmployeeService(employeeRepository);
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
}

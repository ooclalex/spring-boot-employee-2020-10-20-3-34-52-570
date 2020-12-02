package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class SpringBootEmployeeApplicationTests {

	@Test
	void should_return_all_employees_when_get_all_given_all_employees() {
		//given
		EmployeeService employeeService = new EmployeeService();
		EmployeeRepository employeeRepository = Mockito.mock(EmployeeRepository.class);
		final List<Employee> expected = Arrays.asList(new Employee(1, "test", 18, 1000, "male"));

		when(employeeRepository.findAll()).thenReturn(expected);

		//when
		final List<Employee> employees = employeeService.getAll();

		//then
		assertEquals(expected, employees);
	}

}

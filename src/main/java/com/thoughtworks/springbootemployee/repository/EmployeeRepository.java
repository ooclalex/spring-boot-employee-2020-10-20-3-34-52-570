package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String> {
    List<Employee> findAllByGender(String gender);
}

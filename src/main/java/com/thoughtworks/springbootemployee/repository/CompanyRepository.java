package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
}

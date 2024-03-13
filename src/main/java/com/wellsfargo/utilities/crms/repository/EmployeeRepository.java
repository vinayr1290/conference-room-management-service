package com.wellsfargo.utilities.crms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellsfargo.utilities.crms.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}

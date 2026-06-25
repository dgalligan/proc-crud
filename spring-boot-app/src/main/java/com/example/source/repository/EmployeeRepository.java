/*
 * T-Mobile Samson
 * Component: backend-api
 * Owner: samson-platform
 * Description: Spring Data JPA repository for Employee entity
 * Confidential - T-Mobile USA Inc. For internal use only.
 */
package com.example.source.repository;

import com.example.source.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}

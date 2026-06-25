/*
 * T-Mobile Samson
 * Component: backend-api
 * Owner: samson-platform
 * Description: Repository integration tests using H2 in-memory database
 * Confidential - T-Mobile USA Inc. For internal use only.
 */
package com.example.source.repository;

import com.example.source.entity.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("h2")
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void shouldSaveAndFindEmployee() {
        Employee employee = new Employee(1001, "JOHN", new BigDecimal("5000.00"));
        employeeRepository.save(employee);

        Optional<Employee> found = employeeRepository.findById(1001);

        assertThat(found).isPresent();
        assertThat(found.get().getEname()).isEqualTo("JOHN");
        assertThat(found.get().getSal()).isEqualByComparingTo(new BigDecimal("5000.00"));
    }

    @Test
    void shouldDeleteEmployee() {
        Employee employee = new Employee(1002, "JANE", new BigDecimal("6000.00"));
        employeeRepository.save(employee);

        employeeRepository.deleteById(1002);

        assertThat(employeeRepository.findById(1002)).isEmpty();
    }

    @Test
    void shouldReturnEmptyForNonExistent() {
        Optional<Employee> found = employeeRepository.findById(9999);
        assertThat(found).isEmpty();
    }
}

/*
 * T-Mobile Samson
 * Component: backend-api
 * Owner: samson-platform
 * Description: Unit tests for EmployeeService with mocked repository
 * Confidential - T-Mobile USA Inc. For internal use only.
 */
package com.example.source.service;

import com.example.source.dto.EmployeeCreateRequest;
import com.example.source.dto.EmployeeResponse;
import com.example.source.dto.EmployeeUpdateRequest;
import com.example.source.entity.Employee;
import com.example.source.exception.ResourceNotFoundException;
import com.example.source.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeService(employeeRepository);
    }

    @Test
    void create_shouldSaveAndReturnResponse() {
        EmployeeCreateRequest request = new EmployeeCreateRequest(1001, "JOHN", new BigDecimal("5000.00"));
        Employee saved = new Employee(1001, "JOHN", new BigDecimal("5000.00"));
        when(employeeRepository.save(any(Employee.class))).thenReturn(saved);

        EmployeeResponse response = employeeService.create(request);

        assertThat(response.empno()).isEqualTo(1001);
        assertThat(response.ename()).isEqualTo("JOHN");
        assertThat(response.sal()).isEqualByComparingTo(new BigDecimal("5000.00"));
    }

    @Test
    void findById_shouldReturnEmployee() {
        Employee employee = new Employee(1001, "JOHN", new BigDecimal("5000.00"));
        when(employeeRepository.findById(1001)).thenReturn(Optional.of(employee));

        EmployeeResponse response = employeeService.findById(1001);

        assertThat(response.empno()).isEqualTo(1001);
    }

    @Test
    void findById_shouldThrowWhenNotFound() {
        when(employeeRepository.findById(9999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.findById(9999))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void findAll_shouldReturnAllEmployees() {
        List<Employee> employees = List.of(
                new Employee(1001, "JOHN", new BigDecimal("5000.00")),
                new Employee(1002, "JANE", new BigDecimal("6000.00"))
        );
        when(employeeRepository.findAll()).thenReturn(employees);

        List<EmployeeResponse> responses = employeeService.findAll();

        assertThat(responses).hasSize(2);
    }

    @Test
    void update_shouldUpdateSalary() {
        Employee employee = new Employee(1001, "JOHN", new BigDecimal("5000.00"));
        when(employeeRepository.findById(1001)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(
                new Employee(1001, "JOHN", new BigDecimal("6000.00")));

        EmployeeResponse response = employeeService.update(1001, new EmployeeUpdateRequest(new BigDecimal("6000.00")));

        assertThat(response.sal()).isEqualByComparingTo(new BigDecimal("6000.00"));
    }

    @Test
    void update_shouldThrowWhenNotFound() {
        when(employeeRepository.findById(9999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.update(9999, new EmployeeUpdateRequest(new BigDecimal("1000.00"))))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void delete_shouldDeleteEmployee() {
        when(employeeRepository.existsById(1001)).thenReturn(true);

        employeeService.delete(1001);

        verify(employeeRepository).deleteById(1001);
    }

    @Test
    void delete_shouldThrowWhenNotFound() {
        when(employeeRepository.existsById(9999)).thenReturn(false);

        assertThatThrownBy(() -> employeeService.delete(9999))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}

/*
 * T-Mobile Samson
 * Component: backend-api
 * Owner: samson-platform
 * Description: Service layer for employee CRUD operations with OTel tracing
 * Confidential - T-Mobile USA Inc. For internal use only.
 */
package com.example.source.service;

import com.example.source.dto.EmployeeCreateRequest;
import com.example.source.dto.EmployeeResponse;
import com.example.source.dto.EmployeeUpdateRequest;
import com.example.source.entity.Employee;
import com.example.source.exception.ResourceNotFoundException;
import com.example.source.repository.EmployeeRepository;
import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.StatusCode;
import io.opentelemetry.api.trace.Tracer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Tracer tracer;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
        this.tracer = GlobalOpenTelemetry.getTracer("com.example.source");
    }

    public EmployeeResponse create(EmployeeCreateRequest request) {
        Span span = tracer.spanBuilder("employee.create").startSpan();
        try {
            span.setAttribute("app.empno", request.empno());
            Employee employee = new Employee(request.empno(), request.ename(), request.sal());
            Employee saved = employeeRepository.save(employee);
            return toResponse(saved);
        } catch (Exception e) {
            span.recordException(e);
            span.setStatus(StatusCode.ERROR, e.getMessage());
            throw e;
        } finally {
            span.end();
        }
    }

    @Transactional(readOnly = true)
    public EmployeeResponse findById(Integer empno) {
        Span span = tracer.spanBuilder("employee.findById").startSpan();
        try {
            span.setAttribute("app.empno", empno);
            Employee employee = employeeRepository.findById(empno)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Employee not found with empno: " + empno));
            return toResponse(employee);
        } catch (Exception e) {
            span.recordException(e);
            span.setStatus(StatusCode.ERROR, e.getMessage());
            throw e;
        } finally {
            span.end();
        }
    }

    @Transactional(readOnly = true)
    public List<EmployeeResponse> findAll() {
        Span span = tracer.spanBuilder("employee.findAll").startSpan();
        try {
            return employeeRepository.findAll().stream()
                    .map(this::toResponse)
                    .toList();
        } catch (Exception e) {
            span.recordException(e);
            span.setStatus(StatusCode.ERROR, e.getMessage());
            throw e;
        } finally {
            span.end();
        }
    }

    public EmployeeResponse update(Integer empno, EmployeeUpdateRequest request) {
        Span span = tracer.spanBuilder("employee.update").startSpan();
        try {
            span.setAttribute("app.empno", empno);
            Employee employee = employeeRepository.findById(empno)
                    .orElseThrow(() -> new ResourceNotFoundException(
                            "Employee not found with empno: " + empno));
            employee.setSal(request.sal());
            Employee updated = employeeRepository.save(employee);
            return toResponse(updated);
        } catch (Exception e) {
            span.recordException(e);
            span.setStatus(StatusCode.ERROR, e.getMessage());
            throw e;
        } finally {
            span.end();
        }
    }

    public void delete(Integer empno) {
        Span span = tracer.spanBuilder("employee.delete").startSpan();
        try {
            span.setAttribute("app.empno", empno);
            if (!employeeRepository.existsById(empno)) {
                throw new ResourceNotFoundException("Employee not found with empno: " + empno);
            }
            employeeRepository.deleteById(empno);
        } catch (Exception e) {
            span.recordException(e);
            span.setStatus(StatusCode.ERROR, e.getMessage());
            throw e;
        } finally {
            span.end();
        }
    }

    private EmployeeResponse toResponse(Employee employee) {
        return new EmployeeResponse(employee.getEmpno(), employee.getEname(), employee.getSal());
    }
}

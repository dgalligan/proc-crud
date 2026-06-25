/*
 * T-Mobile Samson
 * Component: backend-api
 * Owner: samson-platform
 * Description: REST controller exposing employee CRUD endpoints
 * Confidential - T-Mobile USA Inc. For internal use only.
 */
package com.example.source.controller;

import com.example.source.dto.EmployeeCreateRequest;
import com.example.source.dto.EmployeeResponse;
import com.example.source.dto.EmployeeUpdateRequest;
import com.example.source.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> create(@Valid @RequestBody EmployeeCreateRequest request) {
        EmployeeResponse response = employeeService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{empno}")
    public ResponseEntity<EmployeeResponse> findById(@PathVariable Integer empno) {
        return ResponseEntity.ok(employeeService.findById(empno));
    }

    @GetMapping
    public ResponseEntity<List<EmployeeResponse>> findAll() {
        return ResponseEntity.ok(employeeService.findAll());
    }

    @PutMapping("/{empno}")
    public ResponseEntity<EmployeeResponse> update(@PathVariable Integer empno,
                                                   @Valid @RequestBody EmployeeUpdateRequest request) {
        return ResponseEntity.ok(employeeService.update(empno, request));
    }

    @DeleteMapping("/{empno}")
    public ResponseEntity<Void> delete(@PathVariable Integer empno) {
        employeeService.delete(empno);
        return ResponseEntity.noContent().build();
    }
}

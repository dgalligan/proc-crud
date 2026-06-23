package com.example.source.controller;

import com.example.source.dto.EmpTestRequest;
import com.example.source.dto.EmpTestResponse;
import com.example.source.dto.EmpTestUpdateRequest;
import com.example.source.service.EmpTestService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmpTestController {

    private final EmpTestService empTestService;

    public EmpTestController(EmpTestService empTestService) {
        this.empTestService = empTestService;
    }

    @PostMapping
    public ResponseEntity<EmpTestResponse> create(@Valid @RequestBody EmpTestRequest request) {
        EmpTestResponse response = empTestService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{empno}")
    public ResponseEntity<EmpTestResponse> findById(@PathVariable Integer empno) {
        EmpTestResponse response = empTestService.findById(empno);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<EmpTestResponse>> findAll() {
        List<EmpTestResponse> response = empTestService.findAll();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{empno}")
    public ResponseEntity<EmpTestResponse> update(@PathVariable Integer empno,
                                                   @Valid @RequestBody EmpTestUpdateRequest request) {
        EmpTestResponse response = empTestService.update(empno, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{empno}")
    public ResponseEntity<Void> delete(@PathVariable Integer empno) {
        empTestService.delete(empno);
        return ResponseEntity.noContent().build();
    }
}

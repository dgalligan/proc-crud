package com.example.source.service;

import com.example.source.dto.EmpTestRequest;
import com.example.source.dto.EmpTestResponse;
import com.example.source.dto.EmpTestUpdateRequest;
import com.example.source.entity.EmpTest;
import com.example.source.exception.ResourceNotFoundException;
import com.example.source.repository.EmpTestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmpTestService {

    private final EmpTestRepository empTestRepository;

    public EmpTestService(EmpTestRepository empTestRepository) {
        this.empTestRepository = empTestRepository;
    }

    public EmpTestResponse create(EmpTestRequest request) {
        EmpTest entity = new EmpTest(request.getEmpno(), request.getEname(), request.getSal());
        EmpTest saved = empTestRepository.save(entity);
        return toResponse(saved);
    }

    @Transactional(readOnly = true)
    public EmpTestResponse findById(Integer empno) {
        EmpTest entity = empTestRepository.findById(empno)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with empno: " + empno));
        return toResponse(entity);
    }

    @Transactional(readOnly = true)
    public List<EmpTestResponse> findAll() {
        return empTestRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    public EmpTestResponse update(Integer empno, EmpTestUpdateRequest request) {
        EmpTest entity = empTestRepository.findById(empno)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with empno: " + empno));
        entity.setSal(request.getSal());
        EmpTest updated = empTestRepository.save(entity);
        return toResponse(updated);
    }

    public void delete(Integer empno) {
        EmpTest entity = empTestRepository.findById(empno)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with empno: " + empno));
        empTestRepository.delete(entity);
    }

    private EmpTestResponse toResponse(EmpTest entity) {
        return new EmpTestResponse(entity.getEmpno(), entity.getEname(), entity.getSal());
    }
}

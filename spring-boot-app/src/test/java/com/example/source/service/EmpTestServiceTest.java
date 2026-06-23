package com.example.source.service;

import com.example.source.dto.EmpTestRequest;
import com.example.source.dto.EmpTestResponse;
import com.example.source.dto.EmpTestUpdateRequest;
import com.example.source.entity.EmpTest;
import com.example.source.exception.ResourceNotFoundException;
import com.example.source.repository.EmpTestRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
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
class EmpTestServiceTest {

    @Mock
    private EmpTestRepository empTestRepository;

    @InjectMocks
    private EmpTestService empTestService;

    private EmpTest sampleEntity;

    @BeforeEach
    void setUp() {
        sampleEntity = new EmpTest(1001, "JOHN", new BigDecimal("5000.00"));
    }

    @Test
    void createShouldReturnResponse() {
        when(empTestRepository.save(any(EmpTest.class))).thenReturn(sampleEntity);

        EmpTestRequest request = new EmpTestRequest(1001, "JOHN", new BigDecimal("5000.00"));
        EmpTestResponse response = empTestService.create(request);

        assertThat(response.getEmpno()).isEqualTo(1001);
        assertThat(response.getEname()).isEqualTo("JOHN");
        assertThat(response.getSal()).isEqualByComparingTo(new BigDecimal("5000.00"));
    }

    @Test
    void findByIdShouldReturnResponse() {
        when(empTestRepository.findById(1001)).thenReturn(Optional.of(sampleEntity));

        EmpTestResponse response = empTestService.findById(1001);

        assertThat(response.getEmpno()).isEqualTo(1001);
    }

    @Test
    void findByIdShouldThrowWhenNotFound() {
        when(empTestRepository.findById(9999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> empTestService.findById(9999))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void findAllShouldReturnList() {
        when(empTestRepository.findAll()).thenReturn(List.of(sampleEntity));

        List<EmpTestResponse> responses = empTestService.findAll();

        assertThat(responses).hasSize(1);
    }

    @Test
    void updateShouldReturnUpdatedResponse() {
        when(empTestRepository.findById(1001)).thenReturn(Optional.of(sampleEntity));
        when(empTestRepository.save(any(EmpTest.class))).thenAnswer(inv -> inv.getArgument(0));

        EmpTestUpdateRequest request = new EmpTestUpdateRequest(new BigDecimal("6000.00"));
        EmpTestResponse response = empTestService.update(1001, request);

        assertThat(response.getSal()).isEqualByComparingTo(new BigDecimal("6000.00"));
    }

    @Test
    void updateShouldThrowWhenNotFound() {
        when(empTestRepository.findById(9999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> empTestService.update(9999, new EmpTestUpdateRequest(new BigDecimal("1000.00"))))
                .isInstanceOf(ResourceNotFoundException.class);
    }

    @Test
    void deleteShouldSucceed() {
        when(empTestRepository.findById(1001)).thenReturn(Optional.of(sampleEntity));

        empTestService.delete(1001);

        verify(empTestRepository).delete(sampleEntity);
    }

    @Test
    void deleteShouldThrowWhenNotFound() {
        when(empTestRepository.findById(9999)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> empTestService.delete(9999))
                .isInstanceOf(ResourceNotFoundException.class);
    }
}

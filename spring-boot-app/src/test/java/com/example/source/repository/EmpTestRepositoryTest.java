package com.example.source.repository;

import com.example.source.entity.EmpTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("h2")
class EmpTestRepositoryTest {

    @Autowired
    private EmpTestRepository empTestRepository;

    @Test
    void shouldSaveAndFindById() {
        EmpTest emp = new EmpTest(1001, "JOHN", new BigDecimal("5000.00"));
        empTestRepository.save(emp);

        Optional<EmpTest> found = empTestRepository.findById(1001);
        assertThat(found).isPresent();
        assertThat(found.get().getEname()).isEqualTo("JOHN");
        assertThat(found.get().getSal()).isEqualByComparingTo(new BigDecimal("5000.00"));
    }

    @Test
    void shouldDeleteById() {
        EmpTest emp = new EmpTest(1002, "JANE", new BigDecimal("6000.00"));
        empTestRepository.save(emp);

        empTestRepository.deleteById(1002);

        Optional<EmpTest> found = empTestRepository.findById(1002);
        assertThat(found).isEmpty();
    }

    @Test
    void shouldFindAll() {
        empTestRepository.save(new EmpTest(1003, "BOB", new BigDecimal("4000.00")));
        empTestRepository.save(new EmpTest(1004, "ALICE", new BigDecimal("4500.00")));

        assertThat(empTestRepository.findAll()).hasSize(2);
    }
}

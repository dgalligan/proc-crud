package com.example.source.repository;

import com.example.source.entity.EmpTest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpTestRepository extends JpaRepository<EmpTest, Integer> {
}

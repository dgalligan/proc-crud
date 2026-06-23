package com.example.source.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "emp_test")
public class EmpTest {

    @Id
    @Column(name = "empno")
    private Integer empno;

    @Column(name = "ename", length = 20)
    private String ename;

    @Column(name = "sal")
    private BigDecimal sal;

    public EmpTest() {
    }

    public EmpTest(Integer empno, String ename, BigDecimal sal) {
        this.empno = empno;
        this.ename = ename;
        this.sal = sal;
    }

    public Integer getEmpno() {
        return empno;
    }

    public void setEmpno(Integer empno) {
        this.empno = empno;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public BigDecimal getSal() {
        return sal;
    }

    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }
}

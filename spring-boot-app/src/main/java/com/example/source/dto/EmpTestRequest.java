package com.example.source.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public class EmpTestRequest {

    @NotNull
    private Integer empno;

    @NotBlank
    @Size(max = 20)
    private String ename;

    @NotNull
    private BigDecimal sal;

    public EmpTestRequest() {
    }

    public EmpTestRequest(Integer empno, String ename, BigDecimal sal) {
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

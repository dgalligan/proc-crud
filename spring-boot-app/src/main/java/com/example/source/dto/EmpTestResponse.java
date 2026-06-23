package com.example.source.dto;

import java.math.BigDecimal;

public class EmpTestResponse {

    private Integer empno;
    private String ename;
    private BigDecimal sal;

    public EmpTestResponse() {
    }

    public EmpTestResponse(Integer empno, String ename, BigDecimal sal) {
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

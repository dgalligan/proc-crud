package com.example.source.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public class EmpTestUpdateRequest {

    @NotNull
    private BigDecimal sal;

    public EmpTestUpdateRequest() {
    }

    public EmpTestUpdateRequest(BigDecimal sal) {
        this.sal = sal;
    }

    public BigDecimal getSal() {
        return sal;
    }

    public void setSal(BigDecimal sal) {
        this.sal = sal;
    }
}

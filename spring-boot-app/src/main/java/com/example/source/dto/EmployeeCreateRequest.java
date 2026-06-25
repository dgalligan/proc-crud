/*
 * T-Mobile Samson
 * Component: backend-api
 * Owner: samson-platform
 * Description: Request DTO for creating an employee
 * Confidential - T-Mobile USA Inc. For internal use only.
 */
package com.example.source.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record EmployeeCreateRequest(
        @NotNull Integer empno,
        @NotBlank @Size(max = 20) String ename,
        @NotNull BigDecimal sal) {
}

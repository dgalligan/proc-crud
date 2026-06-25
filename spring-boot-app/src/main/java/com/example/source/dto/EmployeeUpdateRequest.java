/*
 * T-Mobile Samson
 * Component: backend-api
 * Owner: samson-platform
 * Description: Request DTO for updating employee salary
 * Confidential - T-Mobile USA Inc. For internal use only.
 */
package com.example.source.dto;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record EmployeeUpdateRequest(@NotNull BigDecimal sal) {
}

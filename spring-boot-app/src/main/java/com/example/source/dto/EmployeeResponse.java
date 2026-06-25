/*
 * T-Mobile Samson
 * Component: backend-api
 * Owner: samson-platform
 * Description: Response DTO for employee data
 * Confidential - T-Mobile USA Inc. For internal use only.
 */
package com.example.source.dto;

import java.math.BigDecimal;

public record EmployeeResponse(Integer empno, String ename, BigDecimal sal) {
}

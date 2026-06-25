/*
 * T-Mobile Samson
 * Component: backend-api
 * Owner: samson-platform
 * Description: Controller integration tests using MockMvc
 * Confidential - T-Mobile USA Inc. For internal use only.
 */
package com.example.source.controller;

import com.example.source.dto.EmployeeCreateRequest;
import com.example.source.dto.EmployeeResponse;
import com.example.source.dto.EmployeeUpdateRequest;
import com.example.source.exception.ResourceNotFoundException;
import com.example.source.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void create_shouldReturn201() throws Exception {
        EmployeeResponse response = new EmployeeResponse(1001, "JOHN", new BigDecimal("5000.00"));
        when(employeeService.create(any(EmployeeCreateRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(
                                new EmployeeCreateRequest(1001, "JOHN", new BigDecimal("5000.00")))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.empno").value(1001))
                .andExpect(jsonPath("$.ename").value("JOHN"));
    }

    @Test
    void findById_shouldReturn200() throws Exception {
        EmployeeResponse response = new EmployeeResponse(1001, "JOHN", new BigDecimal("5000.00"));
        when(employeeService.findById(1001)).thenReturn(response);

        mockMvc.perform(get("/api/employees/1001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empno").value(1001));
    }

    @Test
    void findById_shouldReturn404WhenNotFound() throws Exception {
        when(employeeService.findById(9999)).thenThrow(new ResourceNotFoundException("Employee not found with empno: 9999"));

        mockMvc.perform(get("/api/employees/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findAll_shouldReturn200() throws Exception {
        List<EmployeeResponse> responses = List.of(
                new EmployeeResponse(1001, "JOHN", new BigDecimal("5000.00")));
        when(employeeService.findAll()).thenReturn(responses);

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void update_shouldReturn200() throws Exception {
        EmployeeResponse response = new EmployeeResponse(1001, "JOHN", new BigDecimal("6000.00"));
        when(employeeService.update(eq(1001), any(EmployeeUpdateRequest.class))).thenReturn(response);

        mockMvc.perform(put("/api/employees/1001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new EmployeeUpdateRequest(new BigDecimal("6000.00")))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sal").value(6000.00));
    }

    @Test
    void delete_shouldReturn204() throws Exception {
        mockMvc.perform(delete("/api/employees/1001"))
                .andExpect(status().isNoContent());
    }

    @Test
    void create_shouldReturn400ForInvalidInput() throws Exception {
        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"empno\": null, \"ename\": \"\", \"sal\": null}"))
                .andExpect(status().isBadRequest());
    }
}

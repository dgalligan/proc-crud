package com.example.source.controller;

import com.example.source.dto.EmpTestRequest;
import com.example.source.dto.EmpTestResponse;
import com.example.source.dto.EmpTestUpdateRequest;
import com.example.source.exception.GlobalExceptionHandler;
import com.example.source.exception.ResourceNotFoundException;
import com.example.source.service.EmpTestService;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmpTestController.class)
class EmpTestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpTestService empTestService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createShouldReturn201() throws Exception {
        EmpTestRequest request = new EmpTestRequest(1001, "JOHN", new BigDecimal("5000.00"));
        EmpTestResponse response = new EmpTestResponse(1001, "JOHN", new BigDecimal("5000.00"));
        when(empTestService.create(any(EmpTestRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.empno").value(1001))
                .andExpect(jsonPath("$.ename").value("JOHN"));
    }

    @Test
    void findByIdShouldReturn200() throws Exception {
        EmpTestResponse response = new EmpTestResponse(1001, "JOHN", new BigDecimal("5000.00"));
        when(empTestService.findById(1001)).thenReturn(response);

        mockMvc.perform(get("/api/employees/1001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.empno").value(1001));
    }

    @Test
    void findByIdShouldReturn404WhenNotFound() throws Exception {
        when(empTestService.findById(9999)).thenThrow(new ResourceNotFoundException("Employee not found with empno: 9999"));

        mockMvc.perform(get("/api/employees/9999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void findAllShouldReturn200() throws Exception {
        EmpTestResponse response = new EmpTestResponse(1001, "JOHN", new BigDecimal("5000.00"));
        when(empTestService.findAll()).thenReturn(List.of(response));

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].empno").value(1001));
    }

    @Test
    void updateShouldReturn200() throws Exception {
        EmpTestUpdateRequest request = new EmpTestUpdateRequest(new BigDecimal("6000.00"));
        EmpTestResponse response = new EmpTestResponse(1001, "JOHN", new BigDecimal("6000.00"));
        when(empTestService.update(eq(1001), any(EmpTestUpdateRequest.class))).thenReturn(response);

        mockMvc.perform(put("/api/employees/1001")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.sal").value(6000.00));
    }

    @Test
    void deleteShouldReturn204() throws Exception {
        doNothing().when(empTestService).delete(1001);

        mockMvc.perform(delete("/api/employees/1001"))
                .andExpect(status().isNoContent());
    }

    @Test
    void createWithInvalidBodyShouldReturn400() throws Exception {
        EmpTestRequest request = new EmpTestRequest(null, "", null);

        mockMvc.perform(post("/api/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}

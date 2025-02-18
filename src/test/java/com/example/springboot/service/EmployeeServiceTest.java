package com.example.springboot.service;

import com.example.springboot.dto.EmployeeRequestDto;
import com.example.springboot.dto.EmployeeResponseDto;
import com.example.springboot.mapper.EmployeeMapper;
import com.example.springboot.model.Employee;
import com.example.springboot.repo.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeService employeeService;

    private EmployeeRequestDto employeeRequestDto;
    private EmployeeResponseDto employeeResponseDto;
    private Employee employeeRequest;


    @BeforeEach
    void setUp() {
        employeeRequest = new Employee();
        employeeRequest.setId(1L);
        employeeRequest.setName("John Doe");

        employeeRequestDto = new EmployeeRequestDto(employeeRequest.getName());
        employeeResponseDto = new EmployeeResponseDto(employeeRequest.getId(), employeeRequest.getName());
    }

    @Test
    void shouldAddEmployeeSuccessfully() {
        when(employeeRepository.save(eq(employeeRequest))).thenReturn(employeeRequest);

        when(employeeMapper.fromDto(eq(employeeRequestDto))).thenReturn(employeeRequest);
        when(employeeMapper.toDto(eq(employeeRequest))).thenReturn(employeeResponseDto);

        var result = employeeService.addEmployee(employeeRequestDto);

        assertNotNull(result);
        assertNotNull(result.id());

        assertEquals(employeeRequest.getId(), result.id());
        assertEquals(employeeRequest.getName(), result.name());
    }

    @Test
    void shouldUpdateEmployeeDetailsSuccessfully() {
        when(employeeRepository.save(eq(employeeRequest))).thenReturn(employeeRequest);
        when(employeeRepository.findById(eq(employeeRequest.getId()))).thenReturn(Optional.of(employeeRequest));

        when(employeeMapper.fromDto(eq(employeeRequestDto))).thenReturn(employeeRequest);
        when(employeeMapper.toDto(eq(employeeRequest))).thenReturn(employeeResponseDto);

        var result = employeeService.updateEmployee(employeeRequest.getId(), employeeRequestDto);

        assertNotNull(result);
        assertTrue(result.isPresent());

        assertEquals(employeeRequest.getId(), result.get().id());
        assertEquals(employeeRequest.getName(), result.get().name());
    }
}
package com.example.springboot.mapper;

import com.example.springboot.dto.EmployeeResponseDto;
import com.example.springboot.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeResponseMapper {
    EmployeeResponseDto toDto(Employee employee);

    Employee fromDto(EmployeeResponseDto employeeResponseDto);
}

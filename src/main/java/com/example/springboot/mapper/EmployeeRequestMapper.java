package com.example.springboot.mapper;

import com.example.springboot.dto.EmployeeRequestDto;
import com.example.springboot.model.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeRequestMapper {
    EmployeeRequestDto toDto(Employee employee);

    Employee fromDto(EmployeeRequestDto employeeRequestDto);
}

package com.example.springboot.service;

import com.example.springboot.dto.EmployeeRequestDto;
import com.example.springboot.dto.EmployeeResponseDto;
import com.example.springboot.dto.TaskResponseDto;
import com.example.springboot.mapper.EmployeeRequestMapper;
import com.example.springboot.mapper.EmployeeResponseMapper;
import com.example.springboot.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final EmployeeRequestMapper employeeRequestMapper;
    private final EmployeeResponseMapper employeeResponseMapper;

    public EmployeeResponseDto addEmployee(EmployeeRequestDto requestDto) {
        var employee = employeeRequestMapper.fromDto(requestDto);
        employee = employeeRepository.save(employee);
        return employeeResponseMapper.toDto(employee);
    }

    public Optional<EmployeeResponseDto> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(employeeResponseMapper::toDto);
    }

    public List<EmployeeResponseDto> getAllEmployers() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeResponseMapper::toDto)
                .toList();
    }

    public Optional<EmployeeResponseDto> updateEmployee(Long id, EmployeeRequestDto requestDto) {
        if (employeeRepository.findById(id).isEmpty())
            return Optional.empty();

        var employee = employeeRequestMapper.fromDto(requestDto);
        employee.setId(id);

        return Optional.ofNullable(
                employeeResponseMapper.toDto(
                        employeeRepository.save(employee)));
    }

    public boolean deleteEmployee(Long id) {
        var employee = employeeRepository.findById(id);

        employee.ifPresent(employeeRepository::delete);

        return employee.isPresent();
    }

    public Optional<EmployeeResponseDto> addTaskToEmployee(Long id, Long taskId) {
        //TODO write implementation
        return Optional.empty();
    }

    public List<TaskResponseDto> getTaskOnEmployee(Long id) {
        //TODO write implementation
        return null;
    }

    public boolean deleteTaskFromEmployee(Long id, Long taskId) {
        //TODO write implementation
        return false;
    }
}

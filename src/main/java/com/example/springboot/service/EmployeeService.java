package com.example.springboot.service;

import com.example.springboot.dto.EmployeeRequestDto;
import com.example.springboot.dto.EmployeeResponseDto;
import com.example.springboot.dto.TaskResponseDto;
import com.example.springboot.mapper.EmployeeMapper;
import com.example.springboot.repo.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    private final EmployeeMapper employeeMapper;

    @Transactional
    public EmployeeResponseDto addEmployee(EmployeeRequestDto requestDto) {
        var employee = employeeMapper.fromDto(requestDto);
        employee = employeeRepository.save(employee);
        return employeeMapper.toDto(employee);
    }

    @Transactional
    public Optional<EmployeeResponseDto> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(employeeMapper::toDto);
    }

    @Transactional
    public Page<EmployeeResponseDto> getAllEmployers(Pageable pageable) {
        return employeeRepository
                .findAll(pageable)
                .map(employeeMapper::toDto);
    }

    @Transactional
    public Optional<EmployeeResponseDto> updateEmployee(Long id, EmployeeRequestDto requestDto) {
        if (employeeRepository.findById(id).isEmpty())
            return Optional.empty();

        var employee = employeeMapper.fromDto(requestDto);
        employee.setId(id);

        return Optional.ofNullable(
                employeeMapper.toDto(
                        employeeRepository.save(employee)));
    }

    @Transactional
    public boolean deleteEmployee(Long id) {
        var employee = employeeRepository.findById(id);

        employee.ifPresent(employeeRepository::delete);

        return employee.isPresent();
    }

    @Transactional
    public Optional<EmployeeResponseDto> addTaskToEmployee(Long id, Long taskId) {
        //TODO write implementation
        return Optional.empty();
    }

    @Transactional
    public List<TaskResponseDto> getTaskOnEmployee(Long id) {
        //TODO write implementation
        return null;
    }

    @Transactional
    public boolean deleteTaskFromEmployee(Long id, Long taskId) {
        //TODO write implementation
        return false;
    }


}

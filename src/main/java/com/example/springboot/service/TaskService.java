package com.example.springboot.service;

import com.example.springboot.dto.EmployeeResponseDto;
import com.example.springboot.dto.TaskRequestDto;
import com.example.springboot.dto.TaskResponseDto;
import com.example.springboot.mapper.EmployeeMapper;
import com.example.springboot.mapper.TaskMapper;
import com.example.springboot.repo.EmployeeRepository;
import com.example.springboot.repo.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    private final EmployeeMapper employeeMapper;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public TaskResponseDto addTask(TaskRequestDto requestDto) {
        var task = taskMapper.fromDto(requestDto);
        task = taskRepository.save(task);
        return taskMapper.toDto(task);
    }

    @Transactional
    public Optional<TaskResponseDto> addEmployeeToTask(Long id, Long employeeId) {
        var task = taskRepository.findById(id);
        var employee = employeeRepository.findById(employeeId);

        if(task.isEmpty() || employee.isEmpty()) {
            return Optional.empty();
        }

        task.get().addEmployee(employee.get());

        taskRepository.save(task.get());
        employeeRepository.save(employee.get());

        return Optional.ofNullable(taskMapper.toDto(task.get()));
    }

    @Transactional
    public Optional<TaskResponseDto> getTaskById(Long id) {
        return taskRepository.findById(id).map(taskMapper::toDto);
    }

    @Transactional
    public Page<TaskResponseDto> getAllTasks(Pageable pageable) {
        return taskRepository.findAll(pageable).map(taskMapper::toDto);
    }

    @Transactional
    public Set<EmployeeResponseDto> getEmployeeOnTask(Long id) {
        return taskRepository.findById(id).map(value -> value.getEmployees()
                        .stream()
                        .map(employeeMapper::toDto)
                        .collect(Collectors.toSet()))
                .orElseGet(HashSet::new);
    }

    @Transactional
    public Optional<TaskResponseDto> updateTask(Long id, TaskRequestDto requestDto) {
        if (taskRepository.findById(id).isEmpty())
            return Optional.empty();

        var task = taskMapper.fromDto(requestDto);
        task.setId(id);

        return Optional.ofNullable(taskMapper.toDto(taskRepository.save(task)));
    }

    @Transactional
    public boolean deleteTask(Long id) {
        var task = taskRepository.findById(id);

        task.ifPresent(taskRepository::delete);

        return task.isPresent();
    }

    @Transactional
    public boolean deleteEmployeeFromTask(Long id, Long employeeId) {
        var task = taskRepository.findById(id);

        if(task.isEmpty()){
            return false;
        }

        var isSuccessful = task.get().removeEmployee(employeeId);

        taskRepository.save(task.get());

        return isSuccessful;
    }
}

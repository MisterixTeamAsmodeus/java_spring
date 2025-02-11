package com.example.springboot.service;

import com.example.springboot.dto.EmployeeResponseDto;
import com.example.springboot.dto.TaskRequestDto;
import com.example.springboot.dto.TaskResponseDto;
import com.example.springboot.mapper.TaskRequestMapper;
import com.example.springboot.mapper.TaskResponseMapper;
import com.example.springboot.repo.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    private final TaskRequestMapper taskRequestMapper;
    private final TaskResponseMapper taskResponseMapper;

    public TaskResponseDto addTask(TaskRequestDto requestDto) {
        var task = taskRequestMapper.fromDto(requestDto);
        task = taskRepository.save(task);
        return taskResponseMapper.toDto(task);
    }

    public Optional<TaskResponseDto> addEmployeeToTask(Long id, Long employeeId) {
        //TODO write implementation
        return Optional.empty();
    }

    public Optional<TaskResponseDto> getTaskById(Long id) {
        return taskRepository.findById(id).map(taskResponseMapper::toDto);
    }

    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskResponseMapper::toDto)
                .toList();
    }

    public List<EmployeeResponseDto> getEmployeeOnTask(Long id) {
        //TODO write implementation
        return new ArrayList<>();
    }

    public Optional<TaskResponseDto> updateTask(Long id, TaskRequestDto requestDto) {
        if (taskRepository.findById(id).isEmpty())
            return Optional.empty();

        var task = taskRequestMapper.fromDto(requestDto);
        task.setId(id);

        return Optional.ofNullable(
                taskResponseMapper.toDto(
                        taskRepository.save(task)));
    }

    public boolean deleteTask(Long id) {
        var task = taskRepository.findById(id);

        task.ifPresent(taskRepository::delete);

        return task.isPresent();
    }

    public boolean deleteEmployeeFromTask(Long id, Long employeeId) {
        //TODO write implementation
        return false;
    }

}

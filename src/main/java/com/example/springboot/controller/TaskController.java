package com.example.springboot.controller;

import com.example.springboot.dto.EmployeeResponseDto;
import com.example.springboot.dto.TaskRequestDto;
import com.example.springboot.dto.TaskResponseDto;
import com.example.springboot.service.TaskService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;

    @PostMapping("/")
    public ResponseEntity<TaskResponseDto> addTask(@Valid @RequestBody TaskRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.addTask(requestDto));
    }

    @PostMapping("/add-employee/{id}")
    public ResponseEntity<TaskResponseDto> addEmployeeToTask(@PathVariable Long id, @RequestParam Long employeeId) {
        return service.addEmployeeToTask(id, employeeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        return service.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public Page<TaskResponseDto> getAllTasks(@ParameterObject Pageable pageable) {
        return service.getAllTasks(pageable);
    }

    @GetMapping("/get-employee-on-task/{id}")
    public ResponseEntity<Set<EmployeeResponseDto>> getEmployeeOnTask(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEmployeeOnTask(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequestDto requestDto) {
        return service.updateTask(id, requestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        return service.deleteTask(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete-employee-from-task/{id}")
    public ResponseEntity<Void> deleteEmployeeFromTask(@PathVariable Long id, @RequestParam Long employeeId) {
        return service.deleteEmployeeFromTask(id, employeeId)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

}
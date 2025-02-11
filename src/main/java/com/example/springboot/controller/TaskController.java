package com.example.springboot.controller;

import com.example.springboot.dto.EmployeeResponseDto;
import com.example.springboot.dto.TaskRequestDto;
import com.example.springboot.dto.TaskResponseDto;
import com.example.springboot.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService service;

    @PostMapping("/add")
    public ResponseEntity<TaskResponseDto> addTask(@RequestBody TaskRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.addTask(requestDto));
    }

    @PostMapping("/add-employee/{id}")
    public ResponseEntity<TaskResponseDto> addEmployeeToTask(@PathVariable Long id, @RequestParam Long employeeId) {
        return service.addEmployeeToTask(id, employeeId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<TaskResponseDto> getTaskById(@PathVariable Long id) {
        return service.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/get")
    public ResponseEntity<List<TaskResponseDto>> getAllTasks() {
        return ResponseEntity.ok(service.getAllTasks());
    }

    @GetMapping("/get-employee-on-task/{id}")
    public ResponseEntity<List<EmployeeResponseDto>> getEmployeeOnTask(@PathVariable Long id) {
        return ResponseEntity.ok(service.getEmployeeOnTask(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TaskResponseDto> updateTask(@PathVariable Long id, @RequestBody TaskRequestDto requestDto) {
        return service.updateTask(id, requestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{id}")
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
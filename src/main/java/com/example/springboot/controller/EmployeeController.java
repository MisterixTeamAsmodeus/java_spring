package com.example.springboot.controller;

import com.example.springboot.dto.EmployeeRequestDto;
import com.example.springboot.dto.EmployeeResponseDto;
import com.example.springboot.dto.TaskResponseDto;
import com.example.springboot.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private EmployeeService service;

    @PostMapping("/")
    public ResponseEntity<EmployeeResponseDto> addEmployee(@RequestBody EmployeeRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.addEmployee(requestDto));
    }

    @PostMapping("/add-task/{id}")
    public ResponseEntity<EmployeeResponseDto> addTaskToEmployee(@PathVariable Long id, @RequestParam Long taskId) {
        return service.addTaskToEmployee(id, taskId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> getEmployeeById(@PathVariable Long id) {
        return service.getEmployeeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/")
    public Page<EmployeeResponseDto> getAll(@ParameterObject Pageable pageable) {
        return service.getAllEmployers(pageable);
    }

    @GetMapping("/get-task-on-employee/{id}")
    public ResponseEntity<List<TaskResponseDto>> getTaskOnEmployee(@PathVariable Long id) {
        return ResponseEntity.ok(service.getTaskOnEmployee(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable Long id, @RequestBody EmployeeRequestDto requestDto) {
        return service.updateEmployee(id, requestDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        return service.deleteEmployee(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete-task-from-employee/{id}")
    public ResponseEntity<Void> deleteTaskFromEmployee(@PathVariable Long id, @RequestParam Long taskId) {
        return service.deleteTaskFromEmployee(id, taskId)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}


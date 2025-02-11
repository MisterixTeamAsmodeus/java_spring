package com.example.springboot.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.example.springboot.model.Employee}
 */
public record EmployeeRequestDto(@NotNull @NotEmpty String name) implements Serializable {
}
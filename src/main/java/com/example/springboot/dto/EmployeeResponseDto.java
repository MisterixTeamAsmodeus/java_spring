package com.example.springboot.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.example.springboot.model.Employee}
 */
public record EmployeeResponseDto(@NotNull Long id,
                                  @NotNull @NotEmpty String name) implements Serializable {
}
package com.example.springboot.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.example.springboot.model.Task}
 */
public record TaskResponseDto(@NotNull Long id,
                              @NotNull @NotEmpty String title,
                              @NotEmpty String description) implements Serializable {
}
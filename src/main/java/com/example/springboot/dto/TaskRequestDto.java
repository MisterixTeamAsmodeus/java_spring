package com.example.springboot.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

/**
 * DTO for {@link com.example.springboot.model.Task}
 */
public record TaskRequestDto(@NotNull @NotEmpty String title,
                              String description) implements Serializable {
}
package com.example.springboot.mapper;

import com.example.springboot.dto.TaskResponseDto;
import com.example.springboot.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskResponseMapper {
    TaskResponseDto toDto(Task task);

    Task fromDto(TaskResponseDto taskRequestDto);
}
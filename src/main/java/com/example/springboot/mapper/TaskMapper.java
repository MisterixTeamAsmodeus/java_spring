package com.example.springboot.mapper;

import com.example.springboot.dto.TaskRequestDto;
import com.example.springboot.dto.TaskResponseDto;
import com.example.springboot.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskResponseDto toDto(Task task);

    Task fromDto(TaskRequestDto taskRequestDto);
}
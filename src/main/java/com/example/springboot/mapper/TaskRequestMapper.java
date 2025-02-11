package com.example.springboot.mapper;

import com.example.springboot.dto.TaskRequestDto;
import com.example.springboot.model.Task;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskRequestMapper {
    TaskRequestDto toDto(Task task);

    Task fromDto(TaskRequestDto taskRequestDto);
}
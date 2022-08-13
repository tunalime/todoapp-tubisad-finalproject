package com.fethitunali.todoapp.service;

import com.fethitunali.todoapp.dto.TaskDto;
import com.fethitunali.todoapp.entity.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {

    // ModelMapper
    public TaskDto entitiyToDto(Task task);

    public Task dtoToEntity(TaskDto taskDto);

    //save
    public TaskDto createTask(TaskDto taskDto);

    //list
    public List<TaskDto> getAllTasks();

    //find
    public ResponseEntity<TaskDto> getTaskById(Long id);

    //update
    public ResponseEntity<TaskDto> updateTaskById(Long id, TaskDto taskDto);

    //delete
    public ResponseEntity<TaskDto> deleteTaskById(Long id);
}

package com.fethitunali.todoapp.service;

import com.fethitunali.todoapp.dto.TaskDto;
import com.fethitunali.todoapp.entity.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskService {

    //save
    public Task createTask(TaskDto taskDto);

    //list
    public List<Task> getAllTasks();

    public List<Task> getAllTasksByDone();

    public List<Task> getAllTasksByUndone();

    //find
    public ResponseEntity<TaskDto> getTaskById(Long id);

    //update
    public boolean updateDescriptionById(Long id, TaskDto taskDto);

    public boolean updateStatusById(Long id);

    //delete
    public boolean deleteTaskById(Long id);
}

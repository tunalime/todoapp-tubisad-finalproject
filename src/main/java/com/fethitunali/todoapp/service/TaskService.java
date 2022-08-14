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

    //find
    public ResponseEntity<Task> getTaskById(Long id);

    //update
    public ResponseEntity<Task> updateDescriptionById(Long id, String description);

    public ResponseEntity<Task> updateStatusById(Long id);

    //delete
    public ResponseEntity<Task> deleteTaskById(Long id);
}

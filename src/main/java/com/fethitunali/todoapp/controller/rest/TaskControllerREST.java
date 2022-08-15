package com.fethitunali.todoapp.controller.rest;

import com.fethitunali.todoapp.dto.TaskDto;
import com.fethitunali.todoapp.entity.Task;
import com.fethitunali.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class TaskControllerREST {

    @Autowired
    TaskService taskService;

    //http://localhost:8080/api/v1
    //http://localhost:8080/api/v1/index
    @GetMapping({"/", "/index"})
    public String getRoot(){
        return "index";
    }

    //http://localhost:8080/api/v1/tasks
    @PostMapping("/tasks")
    public TaskDto createTask(@RequestBody TaskDto taskDto) {
        taskService.createTask(taskDto);
        return taskDto;
    }

    //http://localhost:8080/api/v1/tasks
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    //http://localhost:8080/api/v1/tasks/done
    @GetMapping("/tasks/done")
    public List<Task> getAllTasksDone(){
        return taskService.getAllTasksByDone();
    }

    //http://localhost:8080/api/v1/tasks/undone
    @GetMapping("/tasks/undone")
    public List<Task> getAllTasksUndone(){
        return taskService.getAllTasksByUndone();
    }

    //http://localhost:8080/api/v1/tasks/1
    @GetMapping("/tasks/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    //http://localhost:8080/api/v1/tasks/1
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteTask(@PathVariable Long id) {
        return new ResponseEntity(taskService.deleteTaskById(id), HttpStatus.OK);
    }

    //http://localhost:8080/api/v1/tasks/update/description/1
    @PutMapping("/tasks/update/description/{id}")
    public ResponseEntity<TaskDto> updateTaskDescription(@PathVariable Long id, @RequestBody TaskDto taskDto) {
        return new ResponseEntity(taskService.updateDescriptionById(id, taskDto), HttpStatus.ACCEPTED);
    }

    //http://localhost:8080/api/v1/tasks/update/status/1
    @PutMapping("/tasks/update/status/{id}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id) {
        return new ResponseEntity(taskService.updateStatusById(id), HttpStatus.ACCEPTED);
    }


}

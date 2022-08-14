package com.fethitunali.todoapp.controller.rest;

import com.fethitunali.todoapp.dto.TaskDto;
import com.fethitunali.todoapp.entity.Task;
import com.fethitunali.todoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
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

    //http://localhost:8080/api/v1/tasks/1
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    //http://localhost:8080/api/v1/tasks/1
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        return taskService.deleteTaskById(id);
    }

    //http://localhost:8080/api/v1/tasks/update/description/1
    @PutMapping("/tasks/update/description/{id}")
    public ResponseEntity<Task> updateTaskDescription(@PathVariable Long id, @RequestParam String description) {
        return taskService.updateDescriptionById(id, description);
    }

    //http://localhost:8080/api/v1/tasks/update/status/1
    @PutMapping("/tasks/update/status/{id}")
    public ResponseEntity<Task> updateTaskStatus(@PathVariable Long id) {
        return taskService.updateStatusById(id);
    }


}

package com.fethitunali.todoapp.service.impl;

import com.fethitunali.todoapp.dao.TaskRepository;
import com.fethitunali.todoapp.dto.TaskDto;
import com.fethitunali.todoapp.entity.Task;
import com.fethitunali.todoapp.service.TaskService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Log4j2
public class TaskServiceImpl implements TaskService {

    @Autowired
    TaskRepository taskRepository;


    @Override
    public Task createTask(TaskDto taskDto) {
        Task newTask = new Task();
        newTask.setDescription(taskDto.getDescription());
        newTask.setStatus("undone");
        newTask.setCreatedAt(new Date());
        newTask.setUpdatedAt(new Date());
        taskRepository.save(newTask);
        return newTask;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public ResponseEntity<Task> getTaskById(Long id) {
        try{
            return new ResponseEntity(taskRepository.getById(id), HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity("Kayıt bulunamadı", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Task> updateDescriptionById(Long id, String description) {
        try{
            Task updatedTask = taskRepository.getById(id);
            updatedTask.setDescription(description);
            taskRepository.save(updatedTask);
            return new ResponseEntity(updatedTask, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity("Description güncellenemedi.", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Task> updateStatusById(Long id) {
        try{
            Task updatedTask = taskRepository.getById(id);
            if(updatedTask.getStatus().equals("undone")){
                updatedTask.setStatus("done");
            } else {
                updatedTask.setStatus("undone");
            }
            taskRepository.save(updatedTask);
            return new ResponseEntity(updatedTask, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity("Status Güncellenemedi.", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Task> deleteTaskById(Long id) {
        try{
            Task deletedTask = taskRepository.getById(id);
            taskRepository.deleteById(id);
            return new ResponseEntity(deletedTask, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity("Task Silinemedi.", HttpStatus.BAD_REQUEST);
        }
    }
}

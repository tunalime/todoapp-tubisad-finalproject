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

import java.text.SimpleDateFormat;
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
        newTask.setCreatedAt((new SimpleDateFormat("dd-MM-yy HH:mm")).format(new Date()));
        newTask.setUpdatedAt((new SimpleDateFormat("dd-MM-yy HH:mm")).format(new Date()));
        taskRepository.save(newTask);
        return newTask;
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public ResponseEntity<TaskDto> getTaskById(Long id) {
        try{
            Task task = taskRepository.getById(id);
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.getId());
            taskDto.setDescription(task.getDescription());
            taskDto.setStatus(task.getStatus());
            taskDto.setCreatedAt(task.getCreatedAt());
            taskDto.setUpdatedAt(task.getUpdatedAt());
            return new ResponseEntity(taskDto, HttpStatus.ACCEPTED);
        } catch (Exception e){
            return new ResponseEntity("Kayıt bulunamadı", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public boolean updateDescriptionById(Long id, TaskDto taskDto) {
        try{
            Task updatedTask = taskRepository.getById(id);
            updatedTask.setDescription(taskDto.getDescription());
            taskRepository.save(updatedTask);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean updateStatusById(Long id) {
        try{
            Task updatedTask = taskRepository.getById(id);
            if(updatedTask.getStatus().equals("undone")){
                updatedTask.setStatus("done");
            } else {
                updatedTask.setStatus("undone");
            }
            updatedTask.setUpdatedAt((new SimpleDateFormat("dd-MM-yy HH:mm")).format(new Date()));
            taskRepository.save(updatedTask);
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteTaskById(Long id) {
        try{
            taskRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
}

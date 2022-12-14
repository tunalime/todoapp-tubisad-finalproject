package com.fethitunali.todoapp.controller.mvc;

import com.fethitunali.todoapp.dto.TaskDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import javax.validation.Valid;
import java.util.List;

@Controller
@Log4j2
public class TaskControllerMVC {

    //Speed Data Insert
    //http://localhost:8080/task/speedSave
    @ResponseBody
    @GetMapping("task/speedSave")
    public String speedDataInsert() {
        String URL = "http://localhost:8080/api/v1/tasks";
        RestTemplate restTemplate= new RestTemplate();
        int i = 0;
        for (i = 1; i <= 10 ; i++) {
            TaskDto taskDto = TaskDto.builder().id(0L).description("ToDo: " + i).build();
            restTemplate.postForObject(URL, taskDto, TaskDto.class);
        }
        return i+" tane veri eklendi";
    }

    //SAVE GET
    //http://localhost:8080/task/form
    @GetMapping("task/form")
    public String taskControllerSaveGetForm(Model model) {
        model.addAttribute("task_save", new TaskDto());
        return "task_save";
    }

    //SAVE POST
    //http://localhost:8080/task/form
    @PostMapping("task/form")
    public String taskControllerSavePostForm(@Valid @ModelAttribute("task_save") TaskDto taskDto, BindingResult bindingResult) {
        RestTemplate restTemplate= new RestTemplate();
        String URL = "http://localhost:8080/api/v1/tasks";

        if(bindingResult.hasErrors()){
            return "task_save";
        }
        restTemplate.postForObject(URL, taskDto, TaskDto.class);
        return "redirect:/task/list";
    }

    //LIST
    //http://localhost:8080/task/list
    @GetMapping("task/list")
    public String taskControllerList(Model model) {
        RestTemplate restTemplate= new RestTemplate();
        String URL = "http://localhost:8080/api/v1/tasks";
        ResponseEntity<List<TaskDto>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<TaskDto>>() {
        });
        List<TaskDto> dtoList = responseEntity.getBody();
        model.addAttribute("task_list", dtoList);
        return "task_list";
    }

    //LIST DONE
    //http://localhost:8080/task/list/done
    @GetMapping("/task/list/done")
    public String taskControllerGetDoneList(Model model){
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8080/api/v1/tasks/done";
        ResponseEntity<List<TaskDto>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<TaskDto>>() {
        });
        List<TaskDto> dtoList = responseEntity.getBody();
        model.addAttribute("task_done_list", dtoList);
        return "task_done_list";
    }

    //LIST UNDONE
    //http://localhost:8080/task/list/done
    @GetMapping("/task/list/undone")
    public String taskControllerGetUndoneList(Model model){
        RestTemplate restTemplate = new RestTemplate();
        String URL = "http://localhost:8080/api/v1/tasks/undone";
        ResponseEntity<List<TaskDto>> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, new ParameterizedTypeReference<List<TaskDto>>() {
        });
        List<TaskDto> dtoList = responseEntity.getBody();
        model.addAttribute("task_undone_list", dtoList);
        return "task_undone_list";
    }

    //FIND
    //http://localhost:8080/find/task/4
    @GetMapping("find/task/{id}")
    public String taskControllerFind(@PathVariable(name = "id") Long id, Model model) {
        RestTemplate restTemplate= new RestTemplate();
        String URL = "http://localhost:8080/api/v1/tasks/"+id.toString();
        ResponseEntity<TaskDto> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, TaskDto.class);
        model.addAttribute("task_find", responseEntity.getBody());
        return "task_detail_pages";
    }

    //DELETE
    //http://localhost:8080/task/delete/1
    @GetMapping("delete/task/{id}")
    public String taskControllerDelete(@PathVariable(name = "id") Long id, Model model) {
        //"http://localhost:8080/api/v1/tasks"  /1
        RestTemplate restTemplate= new RestTemplate();
        String URL = "http://localhost:8080/api/v1/tasks/" + id.toString();
        restTemplate.execute(URL,HttpMethod.DELETE,null,null);
        return "redirect:/task/list";
    }

    //UPDATE DESCRIPTION GET
    @GetMapping("update/description/{id}")
    public String taskControllerUpdateDescriptionGetForm(@PathVariable(name = "id") Long id, Model model) {
        RestTemplate restTemplate= new RestTemplate();
        String URL = "http://localhost:8080/api/v1/tasks/" + id;
        ResponseEntity<TaskDto> responseEntity = restTemplate.exchange(URL, HttpMethod.GET, HttpEntity.EMPTY, TaskDto.class);
        model.addAttribute("task_update", responseEntity.getBody());
        return "task_update";
    }

    //UPDATE DESCRIPTION POST
    @PostMapping("update/task/{id}")
    public String taskControllerUpdateDescriptionPostForm(@Valid @ModelAttribute("task_update") TaskDto taskDto, @PathVariable Long id, BindingResult bindingResult) {
        RestTemplate restTemplate= new RestTemplate();
        String URL = "http://localhost:8080/api/v1/tasks/update/description/" + id.toString();

        if(bindingResult.hasErrors()){
            return "task_update";
        }
        restTemplate.put(URL,taskDto);
        return "redirect:/task/list";
    }

    //UPDATE STATUS
    @GetMapping("update/status/{id}")
    public String taskControllerUpdateStatusGetForm(@PathVariable Long id) {
        RestTemplate restTemplate= new RestTemplate();
        String URL = "http://localhost:8080/api/v1/tasks/update/status/" + id.toString();
        restTemplate.execute(URL,HttpMethod.PUT,null,null);
        return "redirect:/task/list";
    }

}

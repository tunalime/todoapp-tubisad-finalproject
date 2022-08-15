package com.fethitunali.todoapp.dao;

import com.fethitunali.todoapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> getAllByStatusOrderById(String status);

    List<Task> getAllByOrderByIdAsc();
}

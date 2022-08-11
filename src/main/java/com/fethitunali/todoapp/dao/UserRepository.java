package com.fethitunali.todoapp.dao;

import com.fethitunali.todoapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

package com.mrxatecnologia.taskmanager.repository;

import com.mrxatecnologia.taskmanager.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {
}
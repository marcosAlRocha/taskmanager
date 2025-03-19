package com.mrxatecnologia.taskmanager.controller;


import com.mrxatecnologia.taskmanager.model.Task;
import com.mrxatecnologia.taskmanager.repository.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping("/tasks")
    public class TaskController {
        private final TaskRepository repository;

        public TaskController(TaskRepository repository) {
            this.repository = repository;
        }

        @PostMapping
        public Task createTask(@RequestBody Task task) {
            return repository.save(task);
        }

        @GetMapping
        public List<Task> getAllTasks() {
            return repository.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
            Optional<Task> task = repository.findById(id);
            return task.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @PutMapping("/{id}")
        public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
            return repository.findById(id).map(task -> {
                task.setTitle(taskDetails.getTitle());
                task.setDescription(taskDetails.getDescription());
                task.setCompleted(taskDetails.isCompleted());
                repository.save(task);
                return ResponseEntity.ok(task);
            }).orElseGet(() -> ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Object> deleteTask(@PathVariable Long id) {
            return repository.findById(id).map(task -> {
                repository.delete(task);
                return ResponseEntity.noContent().build();
            }).orElseGet(() -> ResponseEntity.notFound().build());
        }
}

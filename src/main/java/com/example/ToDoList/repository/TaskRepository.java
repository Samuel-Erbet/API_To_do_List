package com.example.ToDoList.repository;

import com.example.ToDoList.model.Status;
import com.example.ToDoList.model.Tarefa;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tarefa, Long> {


}

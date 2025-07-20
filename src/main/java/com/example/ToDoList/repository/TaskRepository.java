package com.example.ToDoList.repository;

import com.example.ToDoList.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Tarefa, Long> {


}

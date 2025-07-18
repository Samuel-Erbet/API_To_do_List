package com.example.ToDoList.controller;

import com.example.ToDoList.model.Status;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

public class Tasks {
    @Entity
    @Table(name = "Tasks")
    public class Task {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String descricao;
        private Status status;

    }
}

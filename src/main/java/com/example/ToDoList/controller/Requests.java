package com.example.ToDoList.controller;

import com.example.ToDoList.model.Tarefa;
import com.example.ToDoList.repository.TaskRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todolist")
public class Requests {

    private final TaskRepository repository;

    public Requests(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Tarefa> tasks(){
        return repository.findAll();
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,@RequestBody Tarefa novaTarefa){
      repository.findById(id).map(tarefa -> {
          tarefa.setDescricao(novaTarefa.getDescricao());
          tarefa.setStatus(novaTarefa.getStatus());

          return repository.save(tarefa);
      }).orElseThrow();
    }

    @PostMapping
    public void create(@RequestBody Tarefa tarefa){
        repository.save(tarefa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){

        repository.deleteById(id);
    }

}

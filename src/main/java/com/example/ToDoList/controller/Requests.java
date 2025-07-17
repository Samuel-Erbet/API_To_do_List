package com.example.ToDoList.controller;

import com.example.ToDoList.model.Tarefa;
import com.example.ToDoList.repository.TaskRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping(value = "/todolist", produces = {"application/json"})
@Tag(name = "ToDoList-API")
public class Requests {

    private final TaskRepository repository;

    public Requests(TaskRepository repository) {
        this.repository = repository;
    }


    @Operation(summary = "retorna todas as tarefas existentes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "criação de tarefa concluida"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca de dados"),

    })
    @GetMapping()
    public List<Tarefa> tasks(){
        return repository.findAll();
    }

    @Operation(summary = "atualiza as tarefas com base no seu id", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "criação de tarefa concluida"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca de dados"),

    })

    @PutMapping("/{id}")
    public void update(@PathVariable Long id,@RequestBody Tarefa novaTarefa){
      repository.findById(id).map(tarefa -> {
          tarefa.setDescricao(novaTarefa.getDescricao());
          tarefa.setStatus(novaTarefa.getStatus());

          return repository.save(tarefa);
      }).orElseThrow();
    }


    @Operation(summary = "Adiciona uma tarefa nova", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "criação de tarefa concluida"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca de dados"),

    })
    @PostMapping
    public void create(@RequestBody Tarefa tarefa){
        repository.save(tarefa);
    }



    @Operation(summary = "Remove uma tarefa com base no seu id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "criação de tarefa concluida"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca de dados"),

    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){

        repository.deleteById(id);
    }

}

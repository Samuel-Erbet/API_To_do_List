package com.example.ToDoList.controller;

import com.example.ToDoList.model.Tarefa;
import com.example.ToDoList.repository.TaskRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/todolist", produces = {"application/json"})
@Tag(name = "ToDoList-API")

public class Requests {

    @Autowired
    private final TaskRepository repository;

    public Requests(TaskRepository repository) {
        this.repository = repository;
    }


    @Operation(summary = "retorna todas as tarefas existentes", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "criação de tarefa concluida"),
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
            @ApiResponse(responseCode = "201", description = "criação de tarefa concluida"),
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
      })
            .orElseThrow(()-> new EntityNotFoundException("tarefa não encontrada com id "+id));
    }


    @Operation(summary = "Adiciona uma tarefa nova", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "criação de tarefa concluida"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca de dados"),

    })
    @PostMapping
    public ResponseEntity<Tarefa> create(@RequestBody @Valid Tarefa tarefa){

        Tarefa novaTarefa =repository.save(tarefa);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(novaTarefa.getId())
                .toUri();

        return ResponseEntity.created(uri).body(novaTarefa);
    }



    @Operation(summary = "Remove uma tarefa com base no seu id", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "criação de tarefa concluida"),
            @ApiResponse(responseCode = "422", description = "Dados de requisição inválida"),
            @ApiResponse(responseCode = "400", description = "Parametros inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro ao realizar busca de dados"),

    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Tarefa> delete(@PathVariable Long id){
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}

# To Do List API

O To Do List API é uma api construída em java, utilizando o framework Spring, e usando as seguintes tecnologias:

* Spring boot
* Spring Data JPA
* Swagger
* Spring Web
* H2 DataBase

## Funcionalidades

* Criar uma tarefa
* Listar todas as tarefas
* Atualizar uma tarefa
* Deletar uma tarefa
* Buscar uma tarefa por id

## Configuração Application.properties
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

spring.h2.console.enabled=true
```
## Documentação API
A documentação da API pode ser encontrada na rota:
http://localhost:8080/swagger-ui.html

## Como rodar o projeto
## Como rodar o projeto

```bash
# Clone o repositório
git clone https://github.com/Samuel-Erbet/API_To_do_List.git

# Navegue até a pasta do projeto
cd API_To_do_List

# Execute o projeto usando sua IDE favorita ou pelo terminal
./mvnw spring-boot:run

# Caso esteja no Windows e o comando acima não funcione, tente:
mvnw.cmd spring-boot:run
```

## Status
* Projeto em desenvolvimento
* Spring Security ainda não foi implementado
  

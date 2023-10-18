package br.com.isaacsouza.todolist.index;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/")
public class IndexController {
    
     @GetMapping("/")
    public String list(HttpServletRequest request){
        
       return(
        "Esse é um sistema backend  e nele temos 2 endpoints isto é duas url para acessar o nosso serviço "+
        "\n A primeira é a de usuários ao qual podemos cadastrar nossos usuários para tal basta colocar o comando \n"+
        "/users/ após a url do servidor [seu host]/users/\n"+
        "Exemplo: https://localhost:8080/users/"+
        "A segunda é a de Tarefas ao qual podemos criar, resgatar, modificar ou deletar uma tarefa vinculada a um usuário"+
        "\n para tal precisamos de forma analoga ao de usuário colocar o \n"+
        "/tasks/ na url alem disso precisamos que o usuário esteja autenticado para usar essa rota"+
        "\n para isso no momento em que formos manipular as tasks devemos passar o username e o password do nosso usuário"
       );

    }
}

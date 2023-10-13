package br.com.isaacsouza.todolist.task;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.isaacsouza.todolist.utils.Utils;
import io.micrometer.core.ipc.http.HttpSender.Response;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody TaskModel taskModel,HttpServletRequest request){
        UUID idUser = (UUID) request.getAttribute("idUser");
        taskModel.setIdUser(idUser);

        var currentData = LocalDateTime.now();
        if (currentData.isAfter(taskModel.getStartAt()) || currentData.isAfter(taskModel.getEndAt())){
            return ResponseEntity.badRequest()
                .body("A data de inicio / data de termino deve ser maior que a atual");
        }

        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())){
            return ResponseEntity.badRequest()
                .body("A data de inicio deve ser menor que a data de termino");
        }

        var task = taskRepository.save(taskModel);
        System.out.println("Task: "+task);
        return ResponseEntity.status(HttpStatus.CREATED).body(task);

    }
    @GetMapping("/")
    public List<TaskModel> list(HttpServletRequest request){
        var idUser = request.getAttribute("idUser");
        var tasks = this.taskRepository.findByIdUser( (UUID) idUser);
        return tasks;

    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody TaskModel taskModel, @PathVariable UUID id, HttpServletRequest request){
        var task = this.taskRepository.findById(id).orElse(null);
        System.out.println("Task: "+task);
        if(task == null){
            return ResponseEntity. badRequest().body("Tarefa não encontrada");
        }
        
        var idUser = request.getAttribute("idUser");
        System.out.println("id Do User: "+idUser);
        System.out.println("Id do usuário da task: "+task.getIdUser());
        System.out.println("Comparação: "+ task.getIdUser().equals(idUser));
        if(!task.getIdUser().equals(idUser)){
            return ResponseEntity.badRequest().body("Usuário não tem permissão para alterar tarefa");
        }

        Utils.copyNonNullProperties(taskModel, task);

        var taskUpdated = this.taskRepository.save(task);

        return ResponseEntity.ok().body(taskUpdated);
    }
}

package dev.matheuslf.desafio.inscritos.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.matheuslf.desafio.inscritos.Services.TaskService;
import dev.matheuslf.desafio.inscritos.dto.TaskCreateDTO;
import dev.matheuslf.desafio.inscritos.dto.TaskDTO;
import dev.matheuslf.desafio.inscritos.exception.ResourceNotFoundException;
import dev.matheuslf.desafio.inscritos.model.Task;
import dev.matheuslf.desafio.inscritos.model.TaskPriority;
import dev.matheuslf.desafio.inscritos.model.TaskStatus;

import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/tasks")
public class TaskController {
    private TaskService taskService;
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }


    @PostMapping
    public ResponseEntity<TaskDTO> criarTask(@RequestBody TaskCreateDTO taskCreateDTO) {
    Task taskCriada = taskService.savefromDTO(taskCreateDTO);
    TaskDTO taskDTO = taskService.convertToDTO(taskCriada);
    return ResponseEntity.ok(taskDTO);
}




   @GetMapping
    public ResponseEntity<List<TaskDTO>> buscarTasksComFiltros(
    @RequestParam(required = false) Long projectId,
    @RequestParam(required = false) TaskStatus status,
    @RequestParam(required = false) TaskPriority priority) {

    try {
        List<Task> tasks = taskService.findWithFilters(projectId, status, priority);
        
        List<TaskDTO> taskDTOs = tasks.stream()
            .map(taskService::convertToDTO)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(taskDTOs);
        
    } catch (Exception ex) {
        // Log do erro
        throw new ResourceNotFoundException("Error searching tasks with the provided filters");
    }
}

    // Atualizar apenas o status da tarefa
    @PutMapping(value = "/{id}/status")
    public ResponseEntity<TaskDTO> atualizarStatusTask(
    @PathVariable Long id, 
    @RequestParam TaskStatus status) {
    
    // Validação do status
    if (status == null) {
        throw new IllegalArgumentException("Status cannot be null");
    }
    
    Task taskAtualizada = taskService.updateStatus(id, status);
    TaskDTO taskDTO = taskService.convertToDTO(taskAtualizada);
    return ResponseEntity.ok(taskDTO);
}


   @DeleteMapping(value = "/{id}")
   public ResponseEntity<Void>excluirTask(@PathVariable Long id){
    taskService.delete(id);
    return ResponseEntity.noContent().build();
   }



  @GetMapping(value = "/buscar/{id}")
    public ResponseEntity<TaskDTO> buscaTask(@PathVariable Long id) {
        Task task = taskService.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Task", "id", id.toString()));
        
        TaskDTO taskDTO = taskService.convertToDTO(task);
        return ResponseEntity.ok(taskDTO);
    }



   
}

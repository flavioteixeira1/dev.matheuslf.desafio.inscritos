package dev.matheuslf.desafio.inscritos.Services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.matheuslf.desafio.inscritos.Repository.ProjectRepository;
import dev.matheuslf.desafio.inscritos.Repository.TaskRepository;
import dev.matheuslf.desafio.inscritos.dto.ProjecttotaskDTO;
import dev.matheuslf.desafio.inscritos.dto.TaskCreateDTO;
import dev.matheuslf.desafio.inscritos.dto.TaskDTO;
import dev.matheuslf.desafio.inscritos.model.Project;
import dev.matheuslf.desafio.inscritos.model.Task;
import dev.matheuslf.desafio.inscritos.model.TaskStatus;
import dev.matheuslf.desafio.inscritos.model.TaskPriority;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final ProjectRepository projectRepository;
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository, ProjectRepository projectRepository) {
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
    }

    @Transactional
    public Task savefromDTO(TaskCreateDTO taskCreateDTO) {
        if (taskCreateDTO.getProjecttotaskDTO() == null) {
            throw new IllegalArgumentException("ProjecttotaskDTO não pode ser nulo");
        }
        
        // Buscar o projeto ao qual a task pertence
        Project project = projectRepository.findById(taskCreateDTO.getProjecttotaskDTO().getId())
                .orElseThrow(() -> new RuntimeException("Projeto não encontrado com id: " + 
                        taskCreateDTO.getProjecttotaskDTO().getId()));

        Task task = new Task();
        task.setTitle(taskCreateDTO.getTitle());
        task.setDescription(taskCreateDTO.getDescription());
        
        // Converter LocalDateTime para LocalDate se necessário
        if (taskCreateDTO.getDueDate() != null) {
            task.setDueDate(taskCreateDTO.getDueDate().toLocalDate());
        }
        
        task.setStatus(taskCreateDTO.getStatus() != null ? 
                      taskCreateDTO.getStatus() : TaskStatus.TODO);
        task.setPriority(taskCreateDTO.getPriority() != null ? 
                        taskCreateDTO.getPriority() : TaskPriority.MEDIUM);
        task.setProject(project);

        return taskRepository.save(task);
    }

   
    @Transactional
    public Task save(Task task) {
        return taskRepository.save(task);
    }


    public List<Task> findWithFilters(Long projectId, TaskStatus status, TaskPriority priority) {
        if (projectId != null && status != null && priority != null) {
            return taskRepository.findByProjectIdAndStatusAndPriority(projectId, status, priority);
        } else if (projectId != null && status != null) {
            return taskRepository.findByProjectIdAndStatus(projectId, status);
        } else if (projectId != null && priority != null) {
            return taskRepository.findByProjectIdAndPriority(projectId, priority);
        } else if (status != null && priority != null) {
            return taskRepository.findByStatusAndPriority(status, priority);
        } else if (projectId != null) {
            return taskRepository.findByProjectId(projectId);
        } else if (status != null) {
            return taskRepository.findByStatus(status);
        } else if (priority != null) {
            return taskRepository.findByPriority(priority);
        } else {
            return taskRepository.findAll();
        }
    }

  
    @Transactional
    public Task updateStatus(Long taskId, TaskStatus newStatus) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada com id: " + taskId));
        
        task.setStatus(newStatus);
        return taskRepository.save(task);
    }

   
    @Transactional
    public void delete(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new RuntimeException("Tarefa não encontrada com id: " + taskId);
        }
        taskRepository.deleteById(taskId);
    }

    
    public Optional<Task> findById(Long taskId) {
        return taskRepository.findById(taskId);
    }

    
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

   
    
    public TaskDTO convertToDTO(Task task) {
    TaskDTO taskDTO = new TaskDTO();
    taskDTO.setId(task.getId());
    taskDTO.setTitle(task.getTitle());
    taskDTO.setDescription(task.getDescription());
    taskDTO.setTaskStatus(task.getStatus());
    taskDTO.setTaskPriority(task.getPriority());
    taskDTO.setDuedate(task.getDueDate());
    
    //Project para ProjecttotaskDTO
    if (task.getProject() != null) {
        ProjecttotaskDTO projectDTO = new ProjecttotaskDTO(
            task.getProject().getId(), 
            task.getProject().getName()
        );
        taskDTO.setProject(projectDTO);
    }
    
    return taskDTO;
}




}
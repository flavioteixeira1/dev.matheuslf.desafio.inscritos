package dev.matheuslf.desafio.inscritos.Repository;

import dev.matheuslf.desafio.inscritos.model.Task;
import dev.matheuslf.desafio.inscritos.model.TaskStatus;
import dev.matheuslf.desafio.inscritos.model.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
    // Métodos para busca com filtros
    List<Task> findByProjectId(Long projectId);
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByPriority(TaskPriority priority);
    List<Task> findByProjectIdAndStatus(Long projectId, TaskStatus status);
    List<Task> findByProjectIdAndPriority(Long projectId, TaskPriority priority);
    List<Task> findByStatusAndPriority(TaskStatus status, TaskPriority priority);
    List<Task> findByProjectIdAndStatusAndPriority(Long projectId, TaskStatus status, TaskPriority priority);
}
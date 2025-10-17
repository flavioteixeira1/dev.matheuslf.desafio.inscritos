package dev.matheuslf.desafio.inscritos.Repository;

import dev.matheuslf.desafio.inscritos.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    
    
    List<Project> findByNameContainingIgnoreCase(String nome);
    
   
    Optional<Project> findById(Long id);
    
   
    List<Project> findAll();
    
    
    boolean existsById(Long id);
}
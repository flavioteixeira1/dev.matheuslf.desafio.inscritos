package dev.matheuslf.desafio.inscritos.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import dev.matheuslf.desafio.inscritos.Repository.ProjectRepository;
import dev.matheuslf.desafio.inscritos.model.Project;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project save(Project project) throws Exception {
        return projectRepository.save(project);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public List<Project> findByNome(String nome) {
        return projectRepository.findByNameContainingIgnoreCase(nome);
    }

    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    public boolean existsById(Long id) {
        return projectRepository.existsById(id);
    }

    public void deleteById(Long id) {
        projectRepository.deleteById(id);
    }
}
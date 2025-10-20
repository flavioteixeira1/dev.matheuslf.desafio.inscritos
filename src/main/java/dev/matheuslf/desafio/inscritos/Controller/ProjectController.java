package dev.matheuslf.desafio.inscritos.Controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dev.matheuslf.desafio.inscritos.Services.ProjectService;
import dev.matheuslf.desafio.inscritos.exception.ResourceNotFoundException;
import dev.matheuslf.desafio.inscritos.model.Project;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private ProjectService service;

    public ProjectController(ProjectService service) {
        this.service = service;
    }
    
   


    @PostMapping("/")
    public ResponseEntity <Project> salvarProjeto(@RequestBody Project project) throws Exception {

   
        project = service.save(project);
        return ResponseEntity.ok().body(project);
    }

    @GetMapping("/")
    public ResponseEntity<List<Project>> BuscarTodosProjetos() {
		List<Project> project = service.findAll();
		return ResponseEntity.ok().body(project);
	}
    
    @GetMapping("/{id}")
    public ResponseEntity<Project> buscarProjetoPorId(@PathVariable Long id) {
        Project project = service.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Project", "id", id.toString()));
        
        return ResponseEntity.ok().body(project);
    }
    
    

}

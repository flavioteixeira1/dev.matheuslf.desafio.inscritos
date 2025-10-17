package dev.matheuslf.desafio.inscritos.dto;

import java.time.LocalDate;
import dev.matheuslf.desafio.inscritos.model.TaskPriority;
import dev.matheuslf.desafio.inscritos.model.TaskStatus;

public class TaskDTO {

    private Long id;
    private String title;
    private String description;
    private TaskStatus taskStatus;
    private LocalDate dueDate;
    private TaskPriority taskPriority;
    private ProjecttotaskDTO project;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskStatus getStatus(){
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus){
        this.taskStatus = taskStatus;
    }

    public TaskPriority getPriority(){
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority){
        this.taskPriority = taskPriority;
    }

    public LocalDate getDuedate(){
        return dueDate;
    }

    public void setDuedate(LocalDate dueDate){
        this.dueDate = dueDate;
    }

    public ProjecttotaskDTO getProject() {return project;}
    public void setProject(ProjecttotaskDTO projecttotaskDTO) {this.project = projecttotaskDTO;}

}

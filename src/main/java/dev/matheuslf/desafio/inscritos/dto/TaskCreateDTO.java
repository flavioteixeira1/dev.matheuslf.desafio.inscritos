package dev.matheuslf.desafio.inscritos.dto;

import java.time.LocalDateTime;

import dev.matheuslf.desafio.inscritos.model.TaskPriority;
import dev.matheuslf.desafio.inscritos.model.TaskStatus;




public class TaskCreateDTO {
    private Long id;
    private String description;
    private LocalDateTime duedate;
    private TaskStatus status;
    private TaskPriority priority;
    private String title;
    private ProjecttotaskDTO projecttotaskDTO;

    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}
    public String getDescription(){return description;}
    public void setDescription(String description){this.description = description;}
    public LocalDateTime getDueDate(){return duedate;}
    public void setDueDate(LocalDateTime duedate){this.duedate = duedate;}
    public String getTitle(){return title;}
    public void setTitle(String title){this.title = title;}
    public TaskStatus getStatus(){return status;}
    public void setStatus(TaskStatus status){this.status = status;}
    public ProjecttotaskDTO getProjecttotaskDTO(){return projecttotaskDTO;}
    public void setProjecttotaskDTO(ProjecttotaskDTO projecttotaskDTO){this.projecttotaskDTO = projecttotaskDTO;}
    public Long getprojectid(ProjecttotaskDTO projecttotaskDTO){ return projecttotaskDTO.getId();}
    public TaskPriority getPriority(){return priority;}
    public void setPriority(TaskPriority priority){this.priority = priority;} 


}

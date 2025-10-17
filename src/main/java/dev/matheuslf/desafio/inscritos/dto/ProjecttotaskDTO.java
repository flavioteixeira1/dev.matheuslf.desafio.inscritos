package dev.matheuslf.desafio.inscritos.dto;

public class ProjecttotaskDTO {
    private Long id;
    private String name;


    public ProjecttotaskDTO() {
    }


    public ProjecttotaskDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return this.id;
    }
    public String getname(){
        return this.name;
    }
    

}

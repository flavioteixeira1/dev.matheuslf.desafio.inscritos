CREATE TABLE Task (
    id serial PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    description TEXT,
    status VARCHAR(20), 
    priority VARCHAR(20),
    dueDate DATE,
    projectId INTEGER NOT NULL
);

ALTER TABLE Task 
ADD CONSTRAINT fk_task_project
FOREIGN KEY (projectId) 
REFERENCES project(id);
CREATE TABLE project(
    id serial PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    startDate DATE,
    endDate DATE
);
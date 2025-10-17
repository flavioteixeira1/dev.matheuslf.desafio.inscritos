CREATE table project(
    id serial PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT(1000) ,
    startDate DATE,
    endDate DATE
);
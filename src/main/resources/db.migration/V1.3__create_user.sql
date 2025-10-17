CREATE TABLE users(
    id serial PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(20),
    password VARCHAR(255) NOT NULL
);

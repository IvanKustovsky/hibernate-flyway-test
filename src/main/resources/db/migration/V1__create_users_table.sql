CREATE TABLE users
(
    id    BIGINT PRIMARY KEY,
    name  VARCHAR(100),
    email VARCHAR(100) UNIQUE
);
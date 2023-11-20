CREATE DATABASE players_db;

CREATE TABLE IF NOT EXISTS players (
    id UUID PRIMARY KEY,
    name VARCHAR(255),
    surname VARCHAR(255),
    date_birth DATE,
    number INTEGER
    );

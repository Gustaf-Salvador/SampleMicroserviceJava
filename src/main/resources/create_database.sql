CREATE DATABASE IF NOT EXISTS customerdb;

USE customerdb;

CREATE TABLE IF NOT EXISTS customer (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    birthdate DATE NOT NULL,
    create_date_utc TIMESTAMP NOT NULL,
    modified_date_utc TIMESTAMP NULL,
    is_active BOOLEAN NOT NULL
    );

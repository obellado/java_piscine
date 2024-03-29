DROP SCHEMA IF EXISTS chat CASCADE;

CREATE SCHEMA IF NOT EXISTS chat;


CREATE TABLE IF NOT EXISTS chat.users (
    id SERIAL PRIMARY KEY,
    login text UNIQUE NOT NULL,
    password text NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.rooms (
    id SERIAL PRIMARY KEY,
    name TEXT UNIQUE NOT NULL,
    owner INTEGER REFERENCES chat.users(id) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.messages (
    id SERIAL PRIMARY KEY,
    author INTEGER REFERENCES chat.users(id),
    room INTEGER REFERENCES chat.rooms(id),
    text TEXT,
    timestamp TIMESTAMP
    );

DROP USER IF EXISTS root;
CREATE USER root WITH LOGIN PASSWORD '123';
-- Cria o banco de dados
CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

-- Cria a tabela de autores
CREATE TABLE IF NOT EXISTS autores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    informacoes TEXT,
    livros JSON
);



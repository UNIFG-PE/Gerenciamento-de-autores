-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

-- Criação da tabela de autores com campo de exclusão lógica
DROP TABLE IF EXISTS autores;

CREATE TABLE autores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    informacoes TEXT,
);


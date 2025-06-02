-- Cria o banco de dados
CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

-- Cria a tabela de autores
CREATE TABLE IF NOT EXISTS autores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    informacoes TEXT,
    categoria_id INT,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

CREATE TABLE IF NOT EXISTS categorias (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS livros (
    id INT AUTO_INCREMENT PRIMARY KEY,
);

CREATE TABLE  IF NOT EXISTS  autor_livros (
    author_id INT NOT NULL,
    livro_id INT NOT NULL,
    PRIMARY KEY (author_id, livro_id),  -- composite primary key
    FOREIGN KEY (author_id) REFERENCES autores(id),
    FOREIGN KEY (livro_id) REFERENCES livros(id)
);


# 📚 Sistema de Cadastro de Autores

Este projeto é um sistema desktop em Java, feito com **Swing**, que permite cadastrar, listar, atualizar e excluir autores. Os dados são armazenados em um banco de dados **MySQL**.

---

## 🛠 Tecnologias Utilizadas

- Java (JDK 11+)
- Swing (Interface gráfica)
- MySQL (Banco de dados)
- JDBC (Conexão com banco)
- IntelliJ IDEA ou Eclipse

---

## 📁 Estrutura do Projeto

src/
├── Autor.java          # Classe modelo
├── AutorDAO.java       # Classe de acesso ao banco de dados
├── AutorView.java      # Interface gráfica (GUI)
└── Main.java           # Classe principal
---

## 💾 Script do Banco de Dados

```sql
CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

DROP TABLE IF EXISTS autores;

CREATE TABLE autores (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    informacoes TEXT,
    excluido BOOLEAN DEFAULT FALSE
);
```

---

## ✅ Funcionalidades

- Cadastro de novos autores
- Listagem automática dos autores cadastrados
- Exclusão lógica de autores
- Atualização visual em tempo real após operações
- Interface amigável com campos e botões intuitivos

---

## 🔧 Configuração da Conexão com o Banco

No arquivo `AutorDAO.java`, ajuste as credenciais conforme seu ambiente:

```java
connection = DriverManager.getConnection(
    "jdbc:mysql://localhost:3307/biblioteca?useSSL=false&serverTimezone=UTC",
    "root",
    "senha" 
);
```

---

## ▶ Como Executar

1. Certifique-se que o MySQL esteja rodando e o banco de dados esteja criado.
2. Importe o projeto em uma IDE como IntelliJ ou Eclipse.
3. Compile e execute a classe `Main`.
4. A interface será exibida.

---

## 📝 Observações

- A exclusão é **lógica**, ou seja, os autores não são apagados do banco, apenas marcados como excluídos.
- O campo "informações" é opcional.
- O projeto pode ser expandido com atualização e visualização de livros por autor.




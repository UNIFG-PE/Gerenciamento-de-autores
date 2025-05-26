# Usa a imagem oficial mais recente do MariaDB como base
FROM mariadb:latest

# Define a senha do usuário root do MySQL
ENV MYSQL_ROOT_PASSWORD=pao36

# Cria automaticamente o banco de dados chamado "biblioteca" na inicialização
ENV MYSQL_DATABASE=biblioteca

# Cria um novo usuário com permissões p/ o banco "biblioteca"
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=pao36

# Expõe a porta padrão do MariaDB (3306)
EXPOSE 3306

# Copia o script SQL de criação/estruturação do banco de dados para o diretório especial do MariaDB.
# Qualquer arquivo `.sql` colocado em `/docker-entrypoint-initdb.d/` será executado automaticamente
COPY ./banco_biblioteca.sql /docker-entrypoint-initdb.d/


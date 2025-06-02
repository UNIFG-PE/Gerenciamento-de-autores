# Usa a imagem oficial mais recente do MariaDB como base
FROM mariadb:latest

ENV MYSQL_DATABASE=biblioteca

# Set environment variables (change these!)
ENV MYSQL_ROOT_PASSWORD=senha
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=senha

# Expose port 3306
EXPOSE 3306

COPY ./biblioteca.sql /docker-entrypoint-initdb.d/


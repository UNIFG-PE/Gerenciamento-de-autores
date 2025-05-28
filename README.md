build:
```
docker build . -t mariadb
docker run -d -p 3307:3306 mariadb
mvn exec:java
ou executar sem o MySql (caso vc nao ta conseguindo rodar o sql na sua maquina)
mvn exec:java -Dexec.args="offline"
```




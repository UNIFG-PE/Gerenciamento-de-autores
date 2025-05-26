
// Importa classes p/ manipulação de conexão com banco de dados
import java.sql.Connection;
import java.sql.DriverManager;

import com.fasterxml.jackson.databind.ObjectMapper;

//Classe principal que inicia a aplicação e testa a conexão com o banco de dados
public class Main {
    public static void main(String[] args) {

        // Exibe no console que o programa está começando
        System.out.println("working");

        try {
            String json = "[\"hello bob\", \"i am bob\"]";
            ObjectMapper objectMapper = new ObjectMapper();
            String[] livros = objectMapper.readValue(json, String[].class);
            // Utils.json_to_string_array();
            // Indica que está prestes a conectar no banco
            System.out.println("gonig to connect");

            // Cria uma conexão com o banco de dados MariaDB
            // Porta 3307 (ajustado conforme seu ambiente), banco: biblioteca, usuário:
            // root, senha: senha
            Connection conn = DriverManager.getConnection(
                    "jdbc:mariadb://localhost:3307/biblioteca", "root", "senha");

            // Confirma que a conexão foi estabelecida com sucesso
            System.out.println("connection success");

            // Abre a interface gráfica e passa a conexão ativa para ela
            new AutorView(conn);

        } catch (Exception e) {
            // Em caso de erro, exibe a exceção no console
            e.printStackTrace();
        }
    }
}

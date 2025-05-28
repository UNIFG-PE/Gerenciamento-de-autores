
// Importa classes p/ manipulação de conexão com banco de dados
import java.sql.Connection;
import java.sql.DriverManager;

import com.fasterxml.jackson.databind.ObjectMapper;

//C
//
//i
//lasse principal que inicia a aplicação e testa a conexão com o banco de dados
class App {
    public boolean offline;
    public Connection con;

    public App() {
        offline = false;

    }
}

public class Main {
    public static void main(String[] args) {

        // Exibe no console que o programa está começando
        App app = new App();
        for (String arg : args) {
            System.out.println(arg.equals("offline"));
            if (arg.equals("offline")) {
                app.offline = true;
            }
        }

        try {
            // Utils.json_to_string_array();
            // Indica que está prestes a conectar no banco

            // Cria uma conexão com o banco de dados MariaDB
            // Porta 3307 (ajustado conforme seu ambiente), banco: biblioteca, usuário:
            // root, senha: senha
            if (!app.offline) {
                System.out.println("gonig to connect");
                Connection conn = DriverManager.getConnection(
                        "jdbc:mariadb://localhost:3307/biblioteca", "root", "pao36");
                app.con = conn;
            }
            // Confirma que a conexão foi estabelecida com sucesso
            System.out.println("connection success");

            // Abre a interface gráfica e passa a conexão ativa para ela
            new AutorView(app);

        } catch (Exception e) {
            // Em caso de erro, exibe a exceção no console
            e.printStackTrace();
        }
    }
}

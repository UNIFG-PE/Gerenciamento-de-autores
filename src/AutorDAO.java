
// Conexão e comandos SQL
import java.sql.*;
// Listas de autores
import java.util.ArrayList;
import java.util.List;

// Classe para acesso ao banco de dados
public class AutorDAO {
    private Connection connection;
    private boolean offline;
    // Construtor: conecta ao banco
    public AutorDAO() {
        offline = true;
        try {
            // Carrega driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro");
            e.printStackTrace();
        }

        try {
            // Conecta ao banco
            if (!offline) {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3307/biblioteca",
                        "root",
                        "senha");
            }
        } catch (SQLException e) {
            System.out.println("Erro");
            e.printStackTrace();
        }
    }



    public static String column_name(int column) {
        if (column == 0)
            return "id";
        if (column == 1)
            return "nome";
        if (column == 2)
            return "informacoes";
        return "";
    }

    // Insere autor
    public void inserirAutor(Autor autor) {
        if(offline) return;
        String sql = "INSERT INTO autores (nome, informacoes) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getInformacoes());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Lista autores ativos
    public List<Autor> listarAutores() {

        List<Autor> lista = new ArrayList<>();

        if (offline)return lista;
        String sql = "SELECT * FROM autores WHERE excluido = FALSE";
        try (Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Autor autor = new Autor();

                autor.setId(rs.getInt("id"));
                autor.setNome(rs.getString("nome"));
                autor.setInformacoes(rs.getString("informacoes"));
                lista.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    // Atualiza autor pelo ID
    public void atualizarAutor(Autor autor) {
        if (offline) return;
        String sql = "UPDATE autores SET nome = ?, informacoes = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getInformacoes());
            stmt.setInt(3, autor.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Exclui o autor
    public void excluirAutor(int id) {
        if (offline) return;
        String sql = "DELETE FROM autores WHERE id = ?;";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

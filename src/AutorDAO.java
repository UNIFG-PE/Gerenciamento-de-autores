
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {
    private Connection connection;

    public AutorDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro");
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3307/biblioteca?useSSL=false&serverTimezone=UTC",
                    "root",
                    "senha"
            );
        } catch (SQLException e) {
            System.out.println("Erro");
            e.printStackTrace();
        }
    }

    public void inserirAutor(Autor autor) {
        String sql = "INSERT INTO autores (nome, informacoes) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getInformacoes());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Atualiza os dados de um autor com base no ID
    public List<Autor> listarAutores() {
        List<Autor> lista = new ArrayList<>();
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

    public void atualizarAutor(Autor autor) {
        String sql = "UPDATE autores SET nome = ?, informacoes = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getInformacoes());
            stmt.setInt(3, autor.getId());
            stmt.executeUpdate();
       main
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
        
    // Remove um autor do banco com base no ID
   public void excluirAutor(int id) {
        String sql = "UPDATE autores SET excluido = TRUE WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


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

    // Retorna uma lista com todos os autores cadastrados no banco

    public List<Autor> listarAutores() throws SQLException {
        List<Autor> lista = new ArrayList<>(); // Lista q vai armazenar os autores
        String sql = "SELECT * FROM autores"; // Consulta todos os registros da tabela
        // rm.add(txtLivros)

        // Cria um Statement pra executar a consulta
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            // Enquanto houver resultados, cria objetos Autor e add à lista
            while (rs.next()) {

                try {
                    String json = rs.getString("livros");
                    System.out.println(json);
                    if (json == null) {
                        continue;
                    }
                    ObjectMapper objectMapper = new ObjectMapper();
                    String[] livros = objectMapper.readValue(json, String[].class);
                    lista.add(new Autor(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getString("informacoes"),
                            livros));
                } catch (Exception e) {
                    throw new RuntimeException("error converting from db to object");
                }
            }
        }
        return lista; // Retorna a lista completa de autores
    }

    // Atualiza os dados de um autor com base no ID
    public void atualizarAutor(Autor autor) {
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
    
  

    // Remove um autor do banco com base no ID

    public void removerAutor(int id) throws SQLException {
        String sql = "DELETE FROM autores WHERE id = ?";

        // Prepara a query de exclusão
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id); // Define o ID do autor a ser excluído
            stmt.executeUpdate(); // Executa a exclusão
        }
    }
}

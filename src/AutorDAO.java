
// Importa classes p/ conexão com banco de dados e manipulação de listas
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

//Classe DAO responsável por realizar operações no banco de dados p/ a entidade Autor

public class AutorDAO {
    // Conexão com o banco de dados
    private Connection conn;

    // Construtor que recebe uma conexão como parâmetro
    public AutorDAO(Connection conn) {
        this.conn = conn;
    }

    // Insere um novo autor na tabela 'autores'

    public void adicionarAutor(Autor autor) throws SQLException {
        String sql = "INSERT INTO autores (nome, informacoes, livros) VALUES (?, ?, ?)";

        // Usa PreparedStatement para evitar SQL Injection e definir os parâmetros
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, autor.getNome()); // Define o nome no primeiro parâmetro
            stmt.setString(2, autor.getInformacoes()); // Define as informações no segundo parâmetro
            stmt.setString(3, autor.livrosToJson()); // Define as informações no terceiro parâmetro
            stmt.executeUpdate(); // Executa a inserção
        } catch (Exception e) {
            throw new RuntimeException("couldnt insert table into db");
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
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

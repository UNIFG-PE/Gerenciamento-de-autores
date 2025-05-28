import java.sql.*;
import java.util.ArrayList;

public class AutorDAO {
    private Connection conn;

    public AutorDAO() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");
    }

    public void adicionarAutor(Autor autor) throws SQLException {
        String sql = "INSERT INTO autores (nome) VALUES (?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, autor.getNome());
        stmt.executeUpdate();
        stmt.close();
    }

    public ArrayList<Autor> listarAutores() throws SQLException {
        ArrayList<Autor> lista = new ArrayList<>();
        String sql = "SELECT * FROM autores";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("id");
            String nome = rs.getString("nome");
            lista.add(new Autor(id, nome));
        }

        rs.close();
        stmt.close();
        return lista;
    }

    public void atualizarAutor(Autor autor) throws SQLException {
        String sql = "UPDATE autores SET nome=? WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, autor.getNome());
        stmt.setInt(2, autor.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    public void excluirAutor(int id) throws SQLException {
        String sql = "DELETE FROM autores WHERE id=?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        stmt.executeUpdate();
        stmt.close();
    }
}
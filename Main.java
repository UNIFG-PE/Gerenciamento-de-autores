package Autores;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args) {
        try {
            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/biblioteca", "root", "sua_senha"
            );
            new AutorView(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
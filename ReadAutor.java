import java.util.List;

public class ReadAutor {
    public static void listar(List<Autor> autores) {
        if (autores.isEmpty()) {
            System.out.println("Nenhum autor cadastrado.");
        } else {
            for (Autor autor : autores) {
                System.out.println(autor);
            }
        }
    }
}
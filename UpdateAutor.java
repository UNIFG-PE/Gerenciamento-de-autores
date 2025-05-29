import java.util.List;
import java.util.Scanner;

public class UpdateAutor {
    public static void atualizar(List<Autor> autores) {
        Scanner sc = new Scanner(System.in);
        System.out.print("ID do autor que deseja atualizar: ");
        int id = sc.nextInt(); sc.nextLine();
        for (Autor autor : autores) {
            if (autor.id == id) {
                System.out.print("Novo nome: ");
                autor.nome = sc.nextLine();
                System.out.print("Nova informação: ");
                autor.info = sc.nextLine();
                System.out.println("Autor atualizado!");
                return;
            }
        }
        System.out.println("Autor não encontrado.");
    }
}
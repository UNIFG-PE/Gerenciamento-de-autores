import java.util.List;
import java.util.Scanner;

public class CreateAutor {
    public static void adicionar(List<Autor> autores) {
        Scanner sc = new Scanner(System.in);
        System.out.print("ID do autor: ");
        int id = sc.nextInt(); sc.nextLine();
        System.out.print("Nome do autor: ");
        String nome = sc.nextLine();
        System.out.print("Informações adicionais: ");
        String info = sc.nextLine();
        autores.add(new Autor(id, nome, info));
        System.out.println("Autor adicionado com sucesso!");
    }
}
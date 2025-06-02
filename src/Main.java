// Classe principal que inicia o programa
public class Main {
    public static void main(String[] args) {
        // Inicia a interface gráfica na thread correta
        javax.swing.SwingUtilities.invokeLater(() -> {
            new AutorView().setVisible(true); // Abre a janela AutorView
        });
    }
}

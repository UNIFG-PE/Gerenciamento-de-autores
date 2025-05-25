
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.List;

//Classe de interface gráfica p/ gerenciar os autores
public class AutorView extends JFrame {
	
 // Campos de texto e área de exibição
 private JTextField txtNome, txtInfo;
 private JTextArea txtLista;
 private AutorDAO autorDAO; // Objeto responsável pela comunicação com o banco

 // Construtor que recebe uma conexão com o banco de dados
 public AutorView(Connection conn) {
     // Inicializa o DAO com a conexão recebida
     this.autorDAO = new AutorDAO(conn);

     // Define título, o tamanho e o Layout principal como BorderLayout
     setTitle("Gerenciamento de Autores");
     setSize(500, 400);
     setLayout(new BorderLayout());

     // Cria painel com layout em grade para o formulário
     JPanel painelForm = new JPanel(new GridLayout(3, 2));

     // Add campo para:
     painelForm.add(new JLabel("Nome:"));  //nome do autor
     txtNome = new JTextField();
     painelForm.add(txtNome);

    
     painelForm.add(new JLabel("Informações:")); // informações do autor
     txtInfo = new JTextField();
     painelForm.add(txtInfo);

     // Botão para:
     JButton btnAdicionar = new JButton("Adicionar Autor"); //add autor
     painelForm.add(btnAdicionar);

   
     JButton btnAtualizar = new JButton("Atualizar Lista"); //atualizar a lista de autores exibida
     painelForm.add(btnAtualizar);

     // Add painel de formulário na parte superior da janela
     add(painelForm, BorderLayout.NORTH);

     // Área de texto p/ exibir a lista de autores cadastrados
     txtLista = new JTextArea();

     // Add a área de texto com barra de rolagem ao centro da janela
     add(new JScrollPane(txtLista), BorderLayout.CENTER);

     // Define ações dos botões usando expressões lambda
     btnAdicionar.addActionListener(e -> adicionarAutor());
     btnAtualizar.addActionListener(e -> listarAutores());

     // Chama o método que lista os autores assim que a tela abre
     listarAutores();

     // Define o comportamento ao fechar a janela
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

     // Torna a janela visível
     setVisible(true);
 }

 // Método que cria e salva um novo autor com os dados informados
 private void adicionarAutor() {
     try {
         // Cria objeto Autor com os dados dos campos
         Autor autor = new Autor(txtNome.getText(), txtInfo.getText());

         // Salva o autor no banco de dados
         autorDAO.adicionarAutor(autor);

         // Mostra mensagem de sucesso
         JOptionPane.showMessageDialog(this, "Autor adicionado com sucesso!");

         // Atualiza a lista de autores
         listarAutores();
     } catch (SQLException e) {
         e.printStackTrace();
         JOptionPane.showMessageDialog(this, "Erro ao adicionar autor.");
     }
 }

 // Método que consulta e exibe todos os autores cadastrados
 private void listarAutores() {
     try {
         // Obtém a lista de autores do banco
         List<Autor> autores = autorDAO.listarAutores();

         // Limpa o conteúdo da área de exibição
         txtLista.setText("");

         // Percorre a lista e exibe os dados de cada autor
         for (Autor autor : autores) {
             txtLista.append("ID: " + autor.getId() + " | Nome: " + autor.getNome() + " | Info: "
                     + autor.getInformacoes() + "\n");
         }
     } catch (SQLException e) {
         e.printStackTrace();
         txtLista.setText("Erro ao listar autores.");
     }
 }
}

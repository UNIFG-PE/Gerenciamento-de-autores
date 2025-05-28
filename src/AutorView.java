
import javax.swing.*;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class TextWithButton extends JPanel {
    public TextWithButton(AutorView parent, String text, int id) {
        setLayout(new BorderLayout());
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 40)); // for consistent row height

        JLabel label = new JLabel(text);
        JButton button = new JButton("X");
        button.addActionListener(e -> {

            // parent.autorDAO.removerAutor(id);
            // parent.listarAutores();
        });
        button.setForeground(Color.WHITE);
        button.setBackground(Color.RED);

        // Optional: remove focus outline
        button.setFocusPainted(false);

        add(label, BorderLayout.WEST);
        add(button, BorderLayout.EAST);
    }
}

// Classe de interface gráfica p/ gerenciar os autores
public class AutorView extends JFrame {

    // Campos de texto e área de exibição
    private JTextField txtNome, txtInfo, txtLivros;
    private JPanel listPanel;
    public AutorDAO autorDAO; // Objeto responsável pela comunicação com o banco

    // Construtor que recebe uma conexão com o banco de dados
    public AutorView(App app) {
        // Inicializa o DAO com a conexão recebida
        if (!app.offline) {
            this.autorDAO = new AutorDAO(app.con);
        }
        System.out.println("Author View");
        // Define título, o tamanho e o Layout principal como BorderLayout
        setTitle("Gerenciamento de Autores");
        setSize(500, 400);
        setLayout(new BorderLayout());

        // Cria painel com layout em grade para o formulário
        JPanel painelForm = new JPanel(new GridLayout(4, 2));

        // Add campo para:
        painelForm.add(new JLabel("Nome:")); // nome do autor
        txtNome = new JTextField();
        painelForm.add(txtNome);

        painelForm.add(new JLabel("Informações:")); // informações do autor
        txtInfo = new JTextField();
        painelForm.add(txtInfo);

        painelForm.add(new JLabel("Livros:")); // informações do autor
        txtLivros = new JTextField();
        painelForm.add(txtLivros);
        // Botão para:
        JButton btnAdicionar = new JButton("Adicionar Autor"); // add autor
        painelForm.add(btnAdicionar);

        JButton btnAtualizar = new JButton("Atualizar Lista"); // atualizar a lista de autores exibida
        painelForm.add(btnAtualizar);

        // Add painel de formulário na parte superior da janela
        add(painelForm, BorderLayout.NORTH);

        // Área de texto p/ exibir a lista de autores cadastrados
        JPanel listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        // txtLista = new JTextArea();

        // Add a área de texto com barra de rolagem ao centro da janela
        add(new JScrollPane(listPanel), BorderLayout.CENTER);

        // Define ações dos botões usando expressões lambda
        if (!app.offline) {
            btnAdicionar.addActionListener(e -> adicionarAutor(app));
            btnAtualizar.addActionListener(e -> listarAutores(app));
        }
        // Chama o método que lista os autores assim que a tela abre
        listarAutores(app);

        // Define o comportamento ao fechar a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Torna a janela visível
        setVisible(true);
    }

    // Método que cria e salva um novo autor com os dados informados
    private void adicionarAutor(App app) {
        try {
            // Cria objeto Autor com os dados dos campos
            String jsonLivros = txtLivros.getText().trim();

            // Converter JSON string para String[] usando ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();
            String[] livros = objectMapper.readValue(jsonLivros, String[].class);
            Autor autor = new Autor(txtNome.getText(), txtInfo.getText(), livros);

            // Salva o autor no banco de dados
            autorDAO.adicionarAutor(autor);

            // Mostra mensagem de sucesso
            JOptionPane.showMessageDialog(this, "Autor adicionado com sucesso!");

            // Atualiza a lista de autores
            listarAutores(app);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erro ao adicionar autor.");
        }
    }

    // Método que consulta e exibe todos os autores cadastrados
    public void listarAutores(App app) {
        try {
            // Obtém a lista de autores do banco
            List<Autor> autores;
            if (app.offline) {
                autores = new ArrayList();
            } else {
                autores = autorDAO.listarAutores();
            }
            // Limpa o conteúdo da área de exibição
            // txtLista.setText("");

            // Percorre a lista e exibe os dados de cada autor
            for (Autor autor : autores) {
                String livrosStr = "";
                if (autor.getLivros() != null && autor.getLivros().length > 0) {
                    livrosStr = " | Livros: ";
                    for (int i = 0; i < autor.getLivros().length; i++) {
                        livrosStr += autor.getLivros()[i];
                        if (i < autor.getLivros().length - 1) {
                            livrosStr += ", ";
                        }
                    }
                }
                String texto = "ID: " + autor.getId() + " | Nome: " + autor.getNome() + " | Info: "
                        + autor.getInformacoes() + livrosStr;
                listPanel.add(new TextWithButton(this, texto, autor.getId()));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

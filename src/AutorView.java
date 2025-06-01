import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
<<<<<<< Updated upstream
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
=======
import java.util.List;

>>>>>>> Stashed changes
public class AutorView extends JFrame {
    private JTextField txtNome;
    private JTextArea txtInfo;
    private JTable tabelaAutores;
    private DefaultTableModel modeloTabela;

<<<<<<< Updated upstream
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
=======
    public AutorView() {
        setTitle("Cadastro de Autores");
        setSize(700, 450);
>>>>>>> Stashed changes
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout());

<<<<<<< Updated upstream
    // Método que cria e salva um novo autor com os dados informados
    private void adicionarAutor(App app) {
        try {
            // Cria objeto Autor com os dados dos campos
            String jsonLivros = txtLivros.getText().trim();
=======
        JPanel painelEntrada = new JPanel(new GridLayout(5, 1));
        txtNome = new JTextField();
        txtInfo = new JTextArea(3, 20);
        JButton btnCadastrar = new JButton("Cadastrar Autor");
        JButton btnExcluir = new JButton("Excluir Selecionado");
>>>>>>> Stashed changes

        painelEntrada.setBorder(BorderFactory.createTitledBorder("Novo Autor"));
        painelEntrada.add(new JLabel("Nome:"));
        painelEntrada.add(txtNome);
        painelEntrada.add(new JLabel("Informações:"));
        painelEntrada.add(new JScrollPane(txtInfo));
        painelEntrada.add(btnCadastrar);
        painelEntrada.add(btnExcluir);

        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Nome", "Informações"}, 0);
        tabelaAutores = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaAutores);

        painel.add(painelEntrada, BorderLayout.NORTH);
        painel.add(scrollTabela, BorderLayout.CENTER);

<<<<<<< Updated upstream
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

=======
        add(painel);

        btnCadastrar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String info = txtInfo.getText().trim();

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o nome!");
                return;
>>>>>>> Stashed changes
            }

            Autor autor = new Autor();
            autor.setNome(nome);
            autor.setInformacoes(info);

            AutorDAO dao = new AutorDAO();
            dao.inserirAutor(autor);

            JOptionPane.showMessageDialog(null, "Autor cadastrado com sucesso!");
            carregarAutores();
            txtNome.setText("");
            txtInfo.setText("");
        });

        btnExcluir.addActionListener(e -> {
            int linhaSelecionada = tabelaAutores.getSelectedRow();
            if (linhaSelecionada != -1) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Tem certeza que deseja excluir o autor?",
                        "Confirmação",
                        JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    int idAutor = (int) modeloTabela.getValueAt(linhaSelecionada, 0);
                    AutorDAO dao = new AutorDAO();
                    dao.excluirAutor(idAutor);
                    carregarAutores();
                    JOptionPane.showMessageDialog(null, "Autor excluído com sucesso.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir.");
            }
        });

        carregarAutores();
    }

    private void carregarAutores() {
        modeloTabela.setRowCount(0);
        AutorDAO dao = new AutorDAO();
        List<Autor> lista = dao.listarAutores();
        for (Autor a : lista) {
            modeloTabela.addRow(new Object[]{a.getId(), a.getNome(), a.getInformacoes()});
        }
    }
}
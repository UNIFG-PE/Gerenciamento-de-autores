// Importa componentes gráficos e utilitários
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

// Janela principal do sistema
public class AutorView extends JFrame {
    // Campos de entrada e tabela
    private JTextField txtNome;
    private JTextArea txtInfo;
    private JTable tabelaAutores;
    private DefaultTableModel modeloTabela;

    // Construtor da interface
    public AutorView() {
        // Configurações da janela
        setTitle("Cadastro de Autores");
        setSize(700, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza na tela

        JPanel painel = new JPanel(new BorderLayout());

        // Painel com os campos e botões
        JPanel painelEntrada = new JPanel(new GridLayout(5, 1));
        txtNome = new JTextField();
        txtInfo = new JTextArea(3, 20);
        JButton btnCadastrar = new JButton("Cadastrar Autor");
        JButton btnExcluir = new JButton("Excluir Selecionado");

        // Adiciona os componentes ao painel de entrada
        painelEntrada.setBorder(BorderFactory.createTitledBorder("Novo Autor"));
        painelEntrada.add(new JLabel("Nome:"));
        painelEntrada.add(txtNome);
        painelEntrada.add(new JLabel("Informações:"));
        painelEntrada.add(new JScrollPane(txtInfo));
        painelEntrada.add(btnCadastrar);
        painelEntrada.add(btnExcluir);

        // Tabela para exibir autores
        modeloTabela = new DefaultTableModel(new Object[]{"ID", "Nome", "Informações"}, 0);
        tabelaAutores = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaAutores); // Área de rolagem

        // Adiciona painéis à tela
        painel.add(painelEntrada, BorderLayout.NORTH); //cima
        painel.add(scrollTabela, BorderLayout.CENTER); //centro
        add(painel);

        // Ação do botão Cadastrar
        btnCadastrar.addActionListener(e -> {
            String nome = txtNome.getText().trim();
            String info = txtInfo.getText().trim();

            if (nome.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Preencha o nome!");
                return;
            }

            // Cria autor e salva no banco
            Autor autor = new Autor();
            autor.setNome(nome);
            autor.setInformacoes(info);

            AutorDAO dao = new AutorDAO();
            dao.inserirAutor(autor);

            JOptionPane.showMessageDialog(null, "Autor cadastrado com sucesso!");
            carregarAutores(); // Atualiza tabela
            txtNome.setText("");
            txtInfo.setText("");
        });

        // Ação do botão Excluir
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
                    carregarAutores(); // Atualiza tabela
                    JOptionPane.showMessageDialog(null, "Autor excluído com sucesso.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma linha para excluir.");
            }
        });

        carregarAutores(); // Carrega dados ao iniciar
    }

    // Atualiza a tabela com os autores do banco
    private void carregarAutores() {
        modeloTabela.setRowCount(0); // Limpa tabela
        AutorDAO dao = new AutorDAO();
        List<Autor> lista = dao.listarAutores();
        for (Autor a : lista) {
            modeloTabela.addRow(new Object[]{a.getId(), a.getNome(), a.getInformacoes()});
        }
    }
}

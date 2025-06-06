
// Importa componentes gráficos e utilitários
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



// Janela principal do sistema
public class AutorView extends JFrame {
    // Campos de entrada e tabela
    private JTextField txtNome;
    private JTextArea txtInfo;
    private JTable tabelaAutores;
    private DefaultTableModel modeloTabela;


    private List<Autor> lista = new ArrayList<>();

    // Construtor da interface
    public AutorView() {
        // changes.add()
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
        JButton btnAtualizar = new JButton("Atualizar Autor");

        // Adiciona os componentes ao painel de entrada
        painelEntrada.setBorder(BorderFactory.createTitledBorder("Novo Autor"));
        painelEntrada.add(new JLabel("Nome:"));
        painelEntrada.add(txtNome);
        painelEntrada.add(new JLabel("Informações:"));
        painelEntrada.add(new JScrollPane(txtInfo));
        painelEntrada.add(btnCadastrar);
        painelEntrada.add(btnExcluir);
        painelEntrada.add(btnAtualizar);

        // Tabela para exibir autores
        modeloTabela = new DefaultTableModel(new Object[] { "ID", "Nome", "Informações" }, 0);
        tabelaAutores = new JTable(modeloTabela);
        JScrollPane scrollTabela = new JScrollPane(tabelaAutores); // Área de rolagem

        // Adiciona painéis à tela
        painel.add(painelEntrada, BorderLayout.NORTH); // cima
        painel.add(scrollTabela, BorderLayout.CENTER); // centro
        add(painel);

        modeloTabela.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getType() == TableModelEvent.UPDATE) {
                    int column = e.getColumn();
                    if (column == 0) {
                        JOptionPane.showMessageDialog(null, "Alterar id não suportado");
                        carregarAutores();
                        return;
                    }
                    int row = e.getFirstRow();
                    int id = (int) modeloTabela.getValueAt(row, 0);

                    String newValue = (String) modeloTabela.getValueAt(row, column);
                    for (Autor autor : lista) {
                        if (autor.getId() == id) {
                            if (column == 1)
                                autor.setNome(newValue);
                            if (column == 2)
                                autor.setInformacoes(newValue);
                        }
                    }
                    System.out.println("Cell updated at row " + row + ", column " + column +
                            " with value: " + newValue);
                }
            }
        });

        btnAtualizar.addActionListener(e -> {
            AutorDAO dao = new AutorDAO();
            for (Autor autor : lista) {
                dao.atualizarAutor(autor);
            }
            // dao.atualizarAutores(lista);
            carregarAutores();

        });

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
        lista = dao.listarAutores();
        for (Autor a : lista) {
            modeloTabela.addRow(new Object[] { a.getId(), a.getNome(), a.getInformacoes() });
        }
    }
}

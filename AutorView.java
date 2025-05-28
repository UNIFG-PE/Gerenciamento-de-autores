import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class AutorView extends JFrame {
    private JTextField campoNome;
    private JButton botaoAdicionar, botaoAtualizar, botaoExcluir;
    private JList<Autor> listaAutores;
    private DefaultListModel<Autor> modeloLista;

    public AutorView() {
        setTitle("Autores");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        campoNome = new JTextField(20);
        botaoAdicionar = new JButton("Adicionar");
        botaoAtualizar = new JButton("Atualizar");
        botaoExcluir = new JButton("Excluir");

        modeloLista = new DefaultListModel<>();
        listaAutores = new JList<>(modeloLista);
        listaAutores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JLabel("Nome do Autor:"));
        add(campoNome);
        add(botaoAdicionar);
        add(botaoAtualizar);
        add(botaoExcluir);
        add(new JScrollPane(listaAutores));

        botaoAdicionar.addActionListener(e -> adicionarAutor());
        botaoAtualizar.addActionListener(e -> atualizarAutor());
        botaoExcluir.addActionListener(e -> excluirAutor());

        listarAutores();
    }

    private void adicionarAutor() {
        String nome = campoNome.getText();
        if (nome.isEmpty()) return;

        try {
            AutorDAO dao = new AutorDAO();
            dao.adicionarAutor(new Autor(nome));
            campoNome.setText("");
            listarAutores();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void atualizarAutor() {
        Autor selecionado = listaAutores.getSelectedValue();
        if (selecionado == null) return;

        String novoNome = campoNome.getText();
        if (novoNome.isEmpty()) return;

        try {
            selecionado.setNome(novoNome);
            AutorDAO dao = new AutorDAO();
            dao.atualizarAutor(selecionado);
            campoNome.setText("");
            listarAutores();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void excluirAutor() {
        Autor selecionado = listaAutores.getSelectedValue();
        if (selecionado == null) return;

        try {
            AutorDAO dao = new AutorDAO();
            dao.excluirAutor(selecionado.getId());
            campoNome.setText("");
            listarAutores();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void listarAutores() {
        try {
            AutorDAO dao = new AutorDAO();
            modeloLista.clear();
            for (Autor autor : dao.listarAutores()) {
                modeloLista.addElement(autor);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
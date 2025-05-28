//Classe que representa um Autor com ID, nome e informações adicionais

import com.fasterxml.jackson.databind.ObjectMapper;

public class
Autor {
    // Identificador único do autor - usado para atualizações e exclusões
    private int id;
    private String nome; // Nome do autor
    private String informacoes; // Informações complementares sobre o autor
    private String[] livros;

    // Construtor com todos os atributos (usado ao recuperar dados do banco)
    public Autor(int id, String nome, String informacoes, String[] livros) {
        this.id = id;
        this.nome = nome;
        this.informacoes = informacoes;
        this.livros = livros;
    }

    // Construtor sem ID (usado para adicionar novos autores)
    public Autor(String nome, String informacoes, String[] livros) {
        this.nome = nome;
        this.informacoes = informacoes;
        this.livros = livros;
    }

    // Retorna o ID do autor
    public int getId() { // Retorna o ID do autor
        return id;
    }

    public String livrosToJson() {

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(livros);
            return json;
        } catch (Exception e) {
            throw new RuntimeException("couldnt convert livros to json");
        }
    }

    public String[] getLivros() {
        return livros;
    }

    // Retorna o nome do autor
    public String getNome() {
        return nome;
    }

    // Retorna as informações adicionais do autor
    public String getInformacoes() {
        return informacoes;
    }

    // Define o ID do Autor
    public void setId(int id) {
        this.id = id;
    }

    // Define o nome do autor
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Define as informações adicionais do autor
    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }
}

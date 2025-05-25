//Classe que representa um Autor com ID, nome e informações adicionais

public class Autor {
	// Identificador único do autor - usado para atualizações e exclusões
    private int id;
    private String nome; // Nome do autor
    private String informacoes; // Informações complementares sobre o autor

    //Construtor com todos os atributos (usado ao recuperar dados do banco)
    public Autor(int id, String nome, String informacoes) {
        this.id = id; 
        this.nome = nome;
        this.informacoes = informacoes;
    }
    
    //Construtor sem ID (usado para adicionar novos autores)
    public Autor(String nome, String informacoes) {
        this.nome = nome;
        this.informacoes = informacoes;
    }
    
    //Retorna o ID do autor
    public int getId() { //Retorna o ID do autor
        return id;
    }
    
    //Retorna o nome do autor
    public String getNome() {
    	return nome;
    }
    
    //Retorna as informações adicionais do autor
    public String getInformacoes() {
        return informacoes;
    }
 
    //Define o ID do Autor
    public void setId(int id) {
        this.id = id;
    }

    //Define o nome do autor
    public void setNome(String nome) {
        this.nome = nome;
    }

    //Define as informações adicionais do autor
    public void setInformacoes(String informacoes) {
        this.informacoes = informacoes;
    }
}

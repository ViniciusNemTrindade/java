package model.entities;

public class Produto {
    // Atributos da classe Produto
    private String nome;
    private Double preco;
    // Construtor
    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }
    // Dar acesso ao atributo nome
    public String getNome() {
        return nome;
    }
    // Realiza modificação do valor do atributo nome, se necessário
    public void setNome(String nome){
        this.nome = nome;
    }
    // Dar acesso ao atributo preco
    public Double getPreco() {
        return preco;
    }
    // Realiza modificação do valor do atributo preco, se necessário
    public void setPreco(Double preco){
        this.preco = preco;
    }
    
}

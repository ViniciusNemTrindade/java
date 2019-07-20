// @author Vinícius Trindade
package entities;

public class Produto {
        
    private String nome;
    private Double preco;
    
    public Produto(){
    }

    public Produto(String nome, Double preco) {
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }
    
    
    // Método para impressão das etiquetas.
    public String etiquetaPreco(){
        StringBuilder sb = new StringBuilder();
        sb.append(nome);
        sb.append(" R$ ");
        sb.append(String.format("%.2f",preco));
        return sb.toString();
    }
}

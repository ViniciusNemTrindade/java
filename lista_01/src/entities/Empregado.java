package entities;

/**
 * @author vinicius
 */
public class Empregado {
       
    private int id;
    private String nome;
    private double salario;

    public Empregado(Integer id, String nome, Double salario) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
    }
    
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    public void aumentarSalario(double percAumento){
        salario +=  salario*percAumento/100;
    }
    
    public String toString(){
        return getId() + ", " + getNome() + ", "+ String.format("%.2f", salario);
    }
}

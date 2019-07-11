package entities;
/**
 * @author vinicius
 */
public class RegAluguel {
    private String nome;
    private String email;
    private int numQuarto;
    
    public RegAluguel(String nome, String email, int numQuarto){
        this.nome = nome;
        this.email = email;
        this.numQuarto = numQuarto;
    }
    
    public String getNome(){
        return nome;
    }
        
    public String getEmail(){
        return email;
    }
    
    public int getNumQuarto(){
        return numQuarto;
    }
    
    public String toString(){
        return getNumQuarto()
                +": "
                + getNome()
                + ", "
                + getEmail();
    }
    
}

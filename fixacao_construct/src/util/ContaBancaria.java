package util;
/**
 * @author vinicius
 */
public class ContaBancaria {
//     Constante, que contem o valor da taxa de operação, cobrada pelo banco.
    static final double TAX = 5.0;
//    Atributos da classe
    protected int numero;
    private String nome;
    private double saldo;
    
    // Construtor default    
    public ContaBancaria(){
    }
    
// Construtor, que é chamado ao estanciar o objeto, caso o cliente deseje inciar a conta sem  um valor depositado
    public ContaBancaria(int numero,  String nome) {
        
        this.numero = numero;
        this.nome = nome;
    }
// Construtor, que é chamado ao estanciar o objeto, caso o cliente deseje inciar a conta com um valor de depósito.    
    public ContaBancaria(int numero, String nome, double depositoInicial){
        this.numero = numero;
        this.nome = nome;
        deposito(depositoInicial);
    }
    
    public int getNumero(){
        return numero;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
        
    public void deposito(double deposito){
        saldo += deposito;
    }
    
    public void saque(double saque){
        saldo -=  saque;
        saldo -= TAX;
    }
    
    public double getSaldo(){
        return saldo;
    }
    
    public String toString(){
        return "\nNumero da conta: "+ numero + "\nNome: " + nome  + "\nSaldo: "+ saldo;
    }
    
}

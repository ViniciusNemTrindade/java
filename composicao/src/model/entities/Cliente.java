package model.entities;

import java.text.SimpleDateFormat;  // Importação de classe que, converte String para o tipo Date.
import java.util.Date; // Importação de classe para estanciar objetos do tipo data.

public class Cliente {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); // Estanciação do objeto sdf, permitindo formatar a data de acordo com o formato desejado
    // Atributos da classe Cliente
    private String nome;
    private String email;
    private Date dataNascimento;
    // Construtor da classe
    public Cliente(String nome, String email, Date dataNascimento) {
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return nome + " (" + sdf.format(dataNascimento) + ") - " + email;
    }
}

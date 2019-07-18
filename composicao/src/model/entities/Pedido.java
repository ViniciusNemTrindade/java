package model.entities;

import java.text.SimpleDateFormat; // Importação de classe que, permite formatar a data, de acordo com a especificação que se queira.  
import java.util.Date;  // Importação de classe para estanciar objetos do tipo data.    
import java.util.List; // Importação de interface para criar listas.    
import java.util.ArrayList; // importação de classe que implementa a interface List.

import model.enums.StatusPedido; // importação da classe StatusPedido.


public class Pedido {
    // Estanciação do objeto sdf, permitindo formatar a data de acordo com o formato desejado
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
    // Atributos da classe Pedido
    private Date momento; 
    private StatusPedido status;   
    private Cliente cliente;
    // Estanciação da lista de itens do pedido, chamada de interface List, com a classe que a implementa: ArrayList<>()
    private List<ItemPedido> itens = new ArrayList<>();   

    public Pedido(Date momento, StatusPedido status, Cliente cliente) {
        this.momento = momento;
        this.status = status;
        this.cliente = cliente;
    }

    public Date getMomento() {
        return momento;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public void setStatus(StatusPedido status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    // Método para acessar itens da lista
    public List<ItemPedido> getItens(){
        return itens;
    }
    
    // Método para adicionar um elemento da lista itens
    public void adicionarItem(ItemPedido item){
           itens.add(item); // lista de itens, com o método .add() recebendo o item como parâmetro
    }
    
    // Método para remover um elemento da lista itens
    public void removeItem(ItemPedido item){
           itens.remove(item); // lista de itens, com o método .remove(), recebendo o item como parâmetro
    }
    
    // Método que calcula o tota do pedido
    public double total(){
        double soma = 0.0;
        for (ItemPedido item : itens) {
            soma += item.subTotal(); // O método subTotal() está buscando a operação de calculo do mesmo.
        }
        return soma;
    }
    
    @Override
    public String toString() {
        // método para construção de cadeia de strings
        StringBuilder sb = new StringBuilder();
        sb.append("Momento do pedido: ");
        sb.append(sdf.format(momento)+ "\n");
        sb.append("Status do pedido: ");
        sb.append(status + "\n");
        sb.append("Cliente: ");
        sb.append(cliente + "\n");
        sb.append("Itens do pedido: \n");
        for (ItemPedido item : itens) {
            sb.append(item + "\n");
        }
        sb.append("Preco total: R$ ");
        sb.append(String.format("%.2f", total()));
        return sb.toString();
    }
}

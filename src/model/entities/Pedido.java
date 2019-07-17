package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import model.enums.StatusPedido;


public class Pedido {
    
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    
    private Date momento;
    private StatusPedido status;
    private Cliente cliente;
    private List<ItemPedido> itens = new ArrayList<>();  

    public Pedido() {
    }

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
    
    public List<ItemPedido> getItens(){
        return itens;
    }
    
    public void adicionarItem(ItemPedido item){
           itens.add(item);
    }
    
    public void removeItem(ItemPedido item){
           itens.remove(item);
    }
    
    public double total(){
        double soma = 0.0;
        for (ItemPedido item : itens) {
            soma += item.subTotal();
        }
        return soma;
    }
    
    @Override
    public String toString() {
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

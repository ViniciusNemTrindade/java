package application;

import java.text.ParseException; 
import java.util.Scanner;
import java.util.Locale; 
import java.text.SimpleDateFormat;
import java.util.Date;

import model.entities.Cliente;
import model.entities.ItemPedido;
import model.entities.Pedido;
import model.entities.Produto;
import model.enums.StatusPedido;


public class Program {
   
    public static void main(String[] args) throws ParseException {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        System.out.println("Entre com os dados do cliente: ");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Data de nascimento: (DD/MM/YYYY): ");
        Date dataNascimento = sdf.parse(sc.next());
        
        Cliente cliente = new Cliente(nome, email, dataNascimento);
        
        System.out.println("Entre com os dados do pedido: ");
        System.out.println("Status: ");
        StatusPedido status = StatusPedido.valueOf(sc.next());
        Pedido pedido = new Pedido(new Date(), status, cliente);
        ItemPedido itemPed;
        Produto produto;
        System.out.print("Quantos itens terah este pedido? ");
        int qtd = sc.nextInt();
        for (int i = 1; i <= qtd; i++) {
           System.out.println("Entre com os dados do " + i + "# item: ");
            System.out.print("Nome do produto: ");
            sc.nextLine();
            String nomeProd = sc.nextLine();
            System.out.print("Preco do produto: ");
            double precoProd = sc.nextDouble();
            System.out.print("Quantidade do produto: ");
            int quantProd = sc.nextInt();
            itemPed = new ItemPedido(quantProd, precoProd, produto = new Produto(nomeProd, precoProd));
            pedido.adicionarItem(itemPed);
            System.out.println("");
        }
        
        System.out.println("SUMARIO DO PEDIDO: ");
        System.out.println(pedido);
        sc.close();  
    }
    
}

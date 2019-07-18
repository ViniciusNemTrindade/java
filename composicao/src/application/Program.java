package application;

import java.text.ParseException; // Importação de classe que, converte String para o tipo Date.
import java.util.Scanner; 
import java.util.Locale; // Importação de classe que, converte a formatação para o padrão da região desejada. 
import java.text.SimpleDateFormat; // Importação de classe que, permite formatar a data, de acordo com a especificação que se queira.  
import java.util.Date; // Importação de classe para estanciar objetos do tipo data.

// Classes interna que compõem o projeto - composição
import model.entities.Cliente; 
import model.entities.ItemPedido;
import model.entities.Pedido;
import model.entities.Produto;
import model.enums.StatusPedido;


public class Program {
   
    public static void main(String[] args) throws ParseException { // Aplicação da excessão para converter string em tipo data
        
        Locale.setDefault(Locale.US); // Iniciação da classe Locale, com o padrão dos Estados Unidos.
        Scanner sc = new Scanner(System.in);
        // Estanciação do objeto sdf, permitindo formatar a data de acordo com o formato desejado
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy"); 
        
        // Captura dos dados do Cliente.
        System.out.println("Entre com os dados do cliente: ");
        System.out.print("Nome: ");
        String nome = sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Data de nascimento: (DD/MM/YYYY): ");
        Date dataNascimento = sdf.parse(sc.next()); // Aplicação da classe SimpleDateFormat em conjunto com a chamada do método parse da classe ParseException
        
        // Estanciação do objeto cliente, da classe Cliente, passando como parâmetros: nome, email e data de nascimento do cliente 
        Cliente cliente = new Cliente(nome, email, dataNascimento);
        
        // Registro dos dados de um pedio
        System.out.println("Entre com os dados do pedido: ");
        System.out.println("Status: ");
        StatusPedido status = StatusPedido.valueOf(sc.next()); // Conversão de string capturada, passada por parâmetro no método valueOf, para o tipo enum.  
        
        // Estanciação do objeto pedido da classe Pedido. Os parâmetros passados: Date() que captura a data de momento do servidor 
        //  e usá-la com data de ralização do pedido, status e cliente.
        Pedido pedido = new Pedido(new Date(), status, cliente);
     
        System.out.print("Quantos itens terah este pedido? ");
        int qtd = sc.nextInt();
        for (int i = 1; i <= qtd; i++) {
           System.out.println("Entre com os dados do " + i + "# item: ");
            System.out.print("Nome do produto: ");
            sc.nextLine();
            String nomeProd = sc.nextLine();
            System.out.print("Preco do produto: ");
            double precoProd = sc.nextDouble();
            // Estanciação do objeto produto da classe Produto.
            Produto produto = new Produto(nomeProd, precoProd);
            System.out.print("Quantidade do produto: ");
            int quantProd = sc.nextInt();
            // Estanciação do objeto item do pedido( itemPed ), com os parâmetros quantidade do produto(quantProd), precoProd, produto
            ItemPedido itemPed = new ItemPedido(quantProd, precoProd, produto);
            // Adiciona o item ao pedido, com a chamada do método, passando como parâmetro o objeto itemPed  
            pedido.adicionarItem(itemPed);
            System.out.println("");
        }
        
        System.out.println("SUMARIO DO PEDIDO: ");
        // Impressão do objeto pedido
        System.out.println(pedido);
        sc.close();  
    }
    
}

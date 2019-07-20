// @author Vinícius Trindade
package application;

import java.util.Scanner;
import java.util.Locale;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import java.text.ParseException;

import entities.Produto;
import entities.ProdutoImportado;
import entities.ProdutoUsado;

public class Program {

    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        List<Produto> lista = new ArrayList<>();
        
        System.out.print("Entre com a quantidade de produtos: ");
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            System.out.println("Dados do #" + i + " Produto:");
            sc.nextLine();
            System.out.print("Comum, usado ou importado (c/u/i)? ");
            char c = sc.next().charAt(0);
            sc.nextLine();
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Preço: ");
            Double preco = sc.nextDouble();
            if (c == 'i') {
                System.out.print("Taxa alfandegaria: ");
                Double taxAlfandega = sc.nextDouble();
                lista.add(new ProdutoImportado(taxAlfandega, nome, preco));
            } 
            if (c == 'u') {
                System.out.print("Data de fabricação (DD/MM/YYYY): ");
                Date data = sdf.parse(sc.next());
                lista.add(new ProdutoUsado(data, nome, preco));
            } 
            if (c == 'c'){
                Produto produto = new Produto( nome, preco);
                lista.add(produto);
            }
            System.out.println("");
        }
        
        System.out.println("ETIQUETA DE PREÇOS:");
        for (Produto objeto : lista) {
            System.out.println(objeto.etiquetaPreco());
        }
        System.out.println("");
        sc.close();
    }
}

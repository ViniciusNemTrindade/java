// @author Vinícius Trindade
package application;

import java.util.Scanner;
import java.util.Locale;
import model.entities.Conta;
import model.exceptions.DomainException;

public class Program {

    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        try {
            System.out.println("Entre com os dados da conta: ");
            System.out.print("Numero: ");
            Integer num = sc.nextInt();
            sc.nextLine();
            System.out.print("Cliente: ");
            String cliente = sc.nextLine();
            System.out.print("Saldo inicial: ");
            Double saldo = sc.nextDouble();
            System.out.print("Limite de saque: ");
            Double limite = sc.nextDouble();

            Conta conta = new Conta(num, cliente, saldo, limite);
            
            System.out.println("");
            System.out.print("Entre com um valor de saque: ");
            Double valSaque = sc.nextDouble();
            conta.saque(valSaque);
            System.out.println(conta);

        } 
        catch (DomainException e) {
            System.out.println("Operacao invalida: "+ e.getMessage());
        }
        System.out.println(" ");
        sc.close();
    }

}

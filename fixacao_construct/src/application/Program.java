package application;

import java.util.Scanner;
import java.util.Locale;

import util.ContaBancaria;

/**
 * @author vinicius
 */
public class Program {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);

        ContaBancaria conta;

        System.out.printf("CADASTRO DE  CONTA BANCARIA: \n");
        System.out.println("Numero da conta: ");
        int numero = sc.nextInt();
        sc.nextLine();
        System.out.println("Nome do cliente: ");
        String nome = sc.nextLine();
        System.out.println("Deseja faze um deposito inicial ao abrir a conta? digite (1)sim, (2)nao:  ");
        int res = sc.nextInt();
        if (res == 1) {
            System.out.println("Valor inicial para deposito: ");
            double depositoInicial = sc.nextDouble();
            conta = new ContaBancaria(numero, nome, depositoInicial);
        } else {
            conta = new ContaBancaria(numero, nome);
            System.out.println("\nConfirmacao: " + conta);
            System.out.println("");
        }

        System.out.println("");

        System.out.println("OPERACAO DEPOSITO:");
        System.out.println("Valor:  ");
        double val = sc.nextDouble();
        conta.deposito(val);
        System.out.println("\nExtrato: " + conta);
        System.out.println("");

        System.out.println("OPERACAO SAQUE:");
        System.out.println("Valor:  ");
        val = sc.nextDouble();
        conta.saque(val);
        System.out.println("\nExtrato: " + conta);

        sc.close();
    }

}

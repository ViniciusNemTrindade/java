package application;

import java.util.Scanner;
import java.util.Locale;

import util.CurrencyConverter;
/**
 * @author vinicius
 */
public class Program {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        
        System.out.printf("What is the dollar price? ");
        double quote = sc.nextDouble();
        
        System.out.printf("How many dollars will be bought? ");
        double quantity = sc.nextDouble();
        
        double paid;
               paid =  CurrencyConverter.dollar4Real(quote, quantity);
               
        System.out.printf("IOF = %.0f%%\n", CurrencyConverter.IOF);
        System.out.printf("Amout to be paid in reais = %.2f", paid);
        System.out.println(" ");
        
        sc.close();
    }
       
}

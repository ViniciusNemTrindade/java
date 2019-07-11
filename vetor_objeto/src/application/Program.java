package application;
import java.util.Scanner;
import java.util.Locale;
import entities.RegAluguel;
/**
 * @author vinicius
 */
public class Program {

    public static void main(String[] args) {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Quantos alunos deseja registrar? ");
        int n = sc.nextInt();
        RegAluguel reg[] = new RegAluguel[n];
        sc.nextLine();
        
        for(int i=0; i<reg.length; i++){
            System.out.print("Nome: ");
            String nome = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Numero do quarto, opcoes de 1 a 10: ");
            int numQuarto = sc.nextInt();
            reg[i] = new RegAluguel(nome, email, numQuarto);
            sc.nextLine();
            System.out.println("");
        }
        
        System.out.println("");
        System.out.println("Quartos ocupados: ");
        for (int i = 0; i < reg.length; i++) {
            if(reg[i].getNome() != null){
                System.out.print(reg[i]);
                System.out.printf("\n");
            }
        }
        
        sc.close();
    }
    
}

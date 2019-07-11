package application;

import java.util.Scanner;
import java.util.Locale;

import entities.Rectangle;
/**
 * @author vinicius
 */
public class Program {
    public static void main(String[] args) {
        
        Locale.setDefault(Locale.US);
        Scanner input = new Scanner(System.in);
        
        Rectangle rectangle = new Rectangle();
        System.out.println("Enter rectangle width and height: ");
        double r;
        rectangle.width = input.nextDouble();
        rectangle.height = input.nextDouble();
        
        System.out.println(rectangle);
    }
    
}

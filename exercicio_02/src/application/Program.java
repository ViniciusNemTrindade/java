package application;

import java.util.Scanner;
import java.util.Locale;

import entities.Employee;
/**
 * @author vinicius
 */
public class Program {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Locale.setDefault(Locale.US);
        
        Employee employee = new Employee();
        System.out.println("Employee data: ");
        System.out.print("Name: ");
        employee.name = input.nextLine();
        System.out.print("Gross Salary: ");
        employee.grossSalary = input.nextDouble();
        System.out.print("Tax: ");
        employee.tax = input.nextDouble();
        System.out.println("");
        
        System.out.println(employee); // Não é necessário declarar o método toString.
        
        System.out.print("Which percentage to increases salary? ");
        double tax = input.nextDouble();
        
        System.out.println("");
        
        System.out.print("Update data: ");
        employee.increaseSalary(tax);
        input.close();
    }
    
}

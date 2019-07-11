package entities;

/**
 * @author vinicius
 */
public class Employee {
    public String name;
    public double grossSalary;
    public double tax;
    
    public double netSalary(){
        return grossSalary - tax; 
    }
    
    public void increaseSalary(double percentage){
        System.out.printf(name+ ", $ %.2f\n",((percentage * 0.1) * (grossSalary * 0.1)) + netSalary());
    }
    
    public String toString(){
        return "Employee: "+ name+ ", $ "+ String.format("%.2f\n", netSalary());
    }
}

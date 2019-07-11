package entities;
/**
 * @author vinicius
 */
public class Rectangle {
    public double width;
    public double height;
    
    public double area(){
        return width * height; 
    }  
    
    public double perimeter(){
        return 2*(height+width);
    }
    
    public double diagonal(){
        return Math.sqrt(Math.pow(width, 2) + Math.pow(height, 2));
    }
    
    public String toString(){
        return "Area: "+ String.format("%.2f", area()) + "\nPerimeter: "+ String.format("%.2f", perimeter())+ "\nDiagonal: "+ String.format("%.2f", diagonal());
    }
}

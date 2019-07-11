package util;
/**
 * @author vinicius
 */
public class CurrencyConverter {
    public static final double IOF = 6 * 01;
    
    public static double dollar4Real(double dol, double real){
            return (dol * real * IOF / 100) + (dol * real);
    }
}

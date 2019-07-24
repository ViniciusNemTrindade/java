// @author Vinícius Trindade
package model.exceptions;

public class DomainException extends Exception {
    private static final long serialVersionUID = 1L;
    
    public DomainException() {
    }

    public DomainException(String msg) {
        super(msg);
    }
}

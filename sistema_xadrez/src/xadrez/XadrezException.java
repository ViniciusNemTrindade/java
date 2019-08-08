// @author Vinícius Trindade
package xadrez;

import tabuleirodojogo.TabuleiroException;

public class XadrezException extends TabuleiroException {
    private static final long serialVersionUID = 1L;
    
    public XadrezException(String msg) {
        super(msg);
    }
}

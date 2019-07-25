// @author Vinícius Trindade
package xadrez;

import tabuleirodojogo.Peca;
import tabuleirodojogo.Tabuleiro;

public class PecaXadrez extends Peca {

     private Cor cor;
    
    public PecaXadrez(Cor cor, Tabuleiro tabuleiro) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }

    
}

// @author Vinícius Trindade
package xadrez;

import tabuleirodojogo.Peca;
import tabuleirodojogo.Posicao;
import tabuleirodojogo.Tabuleiro;

public abstract class PecaXadrez extends Peca {

     private Cor cor;
    
    public PecaXadrez(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro);
        this.cor = cor;
    }

    public Cor getCor() {
        return cor;
    }
    
    public PosicaoXadrez getPosicaoXadrez() {
        return PosicaoXadrez.paraPosicaoInterface(posicao);
    }
    
    protected boolean haPecaOponente(Posicao posicao) {
        PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
        return p != null && p.getCor() != cor;
    }
    
}

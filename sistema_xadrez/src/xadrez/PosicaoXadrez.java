// @author Vinícius Trindade
package xadrez;

import tabuleirodojogo.Posicao;

public class PosicaoXadrez {
    
    private char coluna;
    private int linha;

    public PosicaoXadrez(char coluna, int linha) {
        if(coluna < 'a' || coluna > 'h' || linha < 1 || linha > 8) {
            throw new XadrezException("Erro ao estanciar PosicaoXadrez. valores possiveis são de a1 a h8."); 
        }
        this.coluna = coluna;
        this.linha = linha;
    }

    public char getColuna() {
        return coluna;
    }

    public int getLinha() {
        return linha;
    }
    
    // Converte o comando de posicão do tabuleiro da interface interativa do jogo, para a posiçao lógica.  
    protected Posicao ParaPosicaoLogica() {
        return new Posicao(8 - linha, coluna - 'a' );
    }
    
    // Faz o inverso do procedimento a cima.
    protected static PosicaoXadrez  ParaPosicaoInterface(Posicao posicao) {
        return new PosicaoXadrez((char)('a' - posicao.getColuna()), 8 - posicao.getLinha());
    }
    
    @Override
    public String toString() {
        return "" + coluna + linha; 
    }
}

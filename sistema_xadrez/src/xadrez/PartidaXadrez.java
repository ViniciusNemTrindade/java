// @author Vinícius Trindade
package xadrez;

import tabuleirodojogo.Tabuleiro;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

    Tabuleiro tabuleiro;

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        configuracaoInicial();
    }

    public PecaXadrez[][] getPecas() {
        PecaXadrez[][] mat = new PecaXadrez[tabuleiro.getLinhas()][tabuleiro.getColunas()];
        for (int i = 0; i < tabuleiro.getLinhas(); i++) {
            for (int j = 0; j < tabuleiro.getColunas(); j++) {
                mat[i][j] = (PecaXadrez) tabuleiro.peca(i, j);
            }
        }
        return mat;
    }
    
    private void alocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.alocarPeca(peca, new PosicaoXadrez(coluna, linha).ParaPosicaoLogica());
    }

    private void configuracaoInicial() {
        alocarNovaPeca( 'b', 6, new Torre(tabuleiro, Cor.WHITE));
        alocarNovaPeca( 'e', 8, new Rei(tabuleiro, Cor.BLACK));
        alocarNovaPeca( 'e', 1, new Rei(tabuleiro, Cor.WHITE));
    }
}

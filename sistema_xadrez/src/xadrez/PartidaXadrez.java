// @author Vinícius Trindade
package xadrez;

import tabuleirodojogo.Posicao;
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

    private void configuracaoInicial() {
        tabuleiro.alocarPeca(new Torre(tabuleiro, Cor.WHITE), new Posicao(2, 1));
        tabuleiro.alocarPeca(new Rei(tabuleiro, Cor.BLACK), new Posicao(0, 4));
        tabuleiro.alocarPeca(new Rei(tabuleiro, Cor.WHITE), new Posicao(7, 4));
    }
}

// @author Vinícius Trindade
package xadrez;

import tabuleirodojogo.Tabuleiro;
import tabuleirodojogo.Peca;
import tabuleirodojogo.Posicao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;

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

    public PecaXadrez realizaMovimentoXadrez(PosicaoXadrez origemPosicao, PosicaoXadrez destinoPosicao) {
        Posicao origem = origemPosicao.paraPosicaoLogica();
        Posicao destino = destinoPosicao.paraPosicaoLogica();
        validarPosicaoOrigem(origem);
        Peca capturaPeca = fazerMovimento(origem, destino);
        return (PecaXadrez) capturaPeca;
    }

    private Peca fazerMovimento(Posicao origem, Posicao destino) {
        Peca p = tabuleiro.removePeca(origem);
        Peca capturaPeca = tabuleiro.removePeca(destino);
        tabuleiro.alocarPeca(p, destino);
        return capturaPeca;
    }

    private void validarPosicaoOrigem(Posicao posicao) {
        if (!tabuleiro.temUmaPosicao(posicao)) {
            throw new XadrezException("Não existe peça na posição de origem");
        }
    }

    private void alocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.alocarPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicaoLogica());
    }

    private void configuracaoInicial() {
        alocarNovaPeca('c', 1, new Torre(tabuleiro, Cor.BRANCA));
        alocarNovaPeca('c', 2, new Torre(tabuleiro, Cor.BRANCA));
        alocarNovaPeca('d', 2, new Torre(tabuleiro, Cor.BRANCA));
        alocarNovaPeca('e', 2, new Torre(tabuleiro, Cor.BRANCA));
        alocarNovaPeca('e', 1, new Torre(tabuleiro, Cor.BRANCA));
        alocarNovaPeca('d', 1, new Rei(tabuleiro, Cor.BRANCA));

        alocarNovaPeca('c', 7, new Torre(tabuleiro, Cor.PRETA));
        alocarNovaPeca('c', 8, new Torre(tabuleiro, Cor.PRETA));
        alocarNovaPeca('d', 7, new Torre(tabuleiro, Cor.PRETA));
        alocarNovaPeca('e', 7, new Torre(tabuleiro, Cor.PRETA));
        alocarNovaPeca('e', 8, new Torre(tabuleiro, Cor.PRETA));
        alocarNovaPeca('d', 8, new Rei(tabuleiro, Cor.PRETA));
    }
}
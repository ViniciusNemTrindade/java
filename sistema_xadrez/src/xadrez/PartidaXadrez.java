// @author Vinícius Trindade
package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import tabuleirodojogo.Tabuleiro;
import tabuleirodojogo.Peca;
import tabuleirodojogo.Posicao;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;
    private int alternarJogador;
    private Cor jogadorAtual;
    private boolean xeque;
    
    private List<Peca> pecasNoTabuleiro = new ArrayList<>(); 
    private List<Peca> pecasCapturadas = new ArrayList<>();

    public PartidaXadrez() {
        tabuleiro = new Tabuleiro(8, 8);
        alternarJogador = 1;
        jogadorAtual = Cor.BRANCA;
        configuracaoInicial();
    }

    public int getAlternarJogador() {
        return alternarJogador;
    }

    public Cor getJogadorAtual() {
        return jogadorAtual;
    }
    
    public boolean getXeque() {
        return xeque;
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

    public boolean[][] movimentosPossiveis(PosicaoXadrez posicaoOrigem) {
        Posicao posicao = posicaoOrigem.paraPosicaoLogica();
        validarPosicaoOrigem(posicao);
        return tabuleiro.peca(posicao).movimentosPossiveis();
    }

    public PecaXadrez realizaMovimentoXadrez(PosicaoXadrez origemPosicao, PosicaoXadrez destinoPosicao) {
        Posicao origem = origemPosicao.paraPosicaoLogica();
        Posicao destino = destinoPosicao.paraPosicaoLogica();
        validarPosicaoOrigem(origem);
        validarPosicaoDestino(origem, destino);
        Peca pecaCapturada = fazerMovimento(origem, destino);
        if (verificaXeque(jogadorAtual)) {
            desfazerMovimento(origem, destino, pecaCapturada);
            throw new XadrezException("Você não pode se colocar em xeque");
        }
        xeque = (verificaXeque(oponente(jogadorAtual))) ? true : false;
        proximaAlternancia();
        return (PecaXadrez)pecaCapturada;
    }

    private Peca fazerMovimento(Posicao origem, Posicao destino) {
        Peca p = tabuleiro.removePeca(origem);
        Peca pecaCapturada = tabuleiro.removePeca(destino);
        tabuleiro.alocarPeca(p, destino);
        
        if (pecaCapturada!= null) {
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }
        
        return pecaCapturada;
    }

    private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
        Peca p = tabuleiro.removePeca(destino);
        tabuleiro.alocarPeca(p, origem);
        
        if (pecaCapturada != null) {
            tabuleiro.alocarPeca(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }
    }
    
    private void validarPosicaoOrigem(Posicao posicao) {
        if (!tabuleiro.temUmaPosicao(posicao)) {
            throw new XadrezException("Não existe peça na posição de origem");
        }
        if (jogadorAtual != ((PecaXadrez)tabuleiro.peca(posicao)).getCor()) {
            throw new XadrezException("A peça escolhida não é a sua");
        }
        if (!tabuleiro.peca(posicao).temAlgumMovimentoPossivel()) {
            throw new XadrezException("Não existe movimentos posssíveis para a peça escolhida");
        }
    }

    private void validarPosicaoDestino(Posicao origem, Posicao destino) {
        if (!tabuleiro.peca(origem).movimentoPossivel(destino)) {
            throw new XadrezException("A peça escolhida não pode se mover para a posição de destino");
        }
    }
    
    private void proximaAlternancia() {
        alternarJogador++;
        jogadorAtual = (jogadorAtual == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
    }

    private Cor oponente(Cor cor) {
        return (cor == Cor.BRANCA) ? Cor.PRETA : Cor.BRANCA;
    }
    
    private PecaXadrez rei(Cor cor) {
        List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : lista) {
            if (p instanceof Rei) {
                return (PecaXadrez)p;
            }
        }
        throw new IllegalStateException("Não existe rei " + cor + "no tabuleiro" );
    }
    
    private boolean verificaXeque(Cor cor) {
        Posicao posicaoRei = rei(cor).getPosicaoXadrez().paraPosicaoLogica();
        List<Peca> pecaOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez)x).getCor() == oponente(cor)).collect(Collectors.toList());
        for (Peca p : pecaOponente) {
            boolean[][] mat = p.movimentosPossiveis();
            if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
                return true;
            }
        }
        return false;
    }
    
    private void alocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.alocarPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicaoLogica());
        pecasNoTabuleiro.add(peca);
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

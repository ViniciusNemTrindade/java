// @author Vinícius Trindade
package xadrez;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import tabuleirodojogo.Tabuleiro;
import tabuleirodojogo.Peca;
import tabuleirodojogo.Posicao;
import xadrez.pecas.Bispo;
import xadrez.pecas.Cavalo;
import xadrez.pecas.Peao;
import xadrez.pecas.Rainha;
import xadrez.pecas.Rei;
import xadrez.pecas.Torre;

public class PartidaXadrez {

    private Tabuleiro tabuleiro;
    private int alternarJogador;
    private Cor jogadorAtual;
    private boolean xeque;
    private boolean xequeMate;
    private PecaXadrez enPassantVulnerabilidade;

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

    public boolean getXequeMate() {
        return xequeMate;
    }

    public PecaXadrez getEnPassantVulnerabilidade() {
        return enPassantVulnerabilidade;
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

        PecaXadrez pecaMovida = (PecaXadrez) tabuleiro.peca(destino);

        xeque = (verificaXeque(oponente(jogadorAtual))) ? true : false;
        if (verificaXequeMate(oponente(jogadorAtual))) {
            xequeMate = true;
        } else {
            proximaAlternancia();
        }

        // #Movimento especial en passant
        if (pecaMovida instanceof Peao && (destino.getLinha() == origem.getLinha() - 2 || destino.getLinha() == origem.getLinha() + 2)) {
            enPassantVulnerabilidade = pecaMovida;
        } else {
            enPassantVulnerabilidade = null;
        }

        return (PecaXadrez) pecaCapturada;
    }

    private Peca fazerMovimento(Posicao origem, Posicao destino) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removePeca(origem);
        p.incrementarQuantMovimeno();
        Peca pecaCapturada = tabuleiro.removePeca(destino);
        tabuleiro.alocarPeca(p, destino);

        if (pecaCapturada != null) {
            pecasNoTabuleiro.remove(pecaCapturada);
            pecasCapturadas.add(pecaCapturada);
        }

        // #Movimento especial Roque pequeno;
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(origemT);
            tabuleiro.alocarPeca(torre, destinoT);
            torre.incrementarQuantMovimeno();
        }
        
        // # Movimento especial Roque grande; 
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
        Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
        Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
        PecaXadrez torre = (PecaXadrez)tabuleiro.removePeca(origemT);
        tabuleiro.alocarPeca(torre, destinoT);
        torre.incrementarQuantMovimeno();
                        }	
        // #Movimento especial En Passant;
        if (p instanceof Peao) {
            if (origem.getColuna() != destino.getColuna() && pecaCapturada == null) {
                Posicao posicaoPeao;
                if (p.getCor() == Cor.BRANCA) {
                    posicaoPeao = new Posicao(destino.getLinha() + 1, destino.getColuna());
                } 
                else {
                    posicaoPeao = new Posicao(destino.getLinha() - 1, destino.getColuna());
                }
                pecaCapturada = tabuleiro.removePeca(posicaoPeao);
                pecasCapturadas.add(pecaCapturada);
                pecasNoTabuleiro.remove(pecaCapturada);      
            }
        }    
        return pecaCapturada;
    }   

    private void desfazerMovimento(Posicao origem, Posicao destino, Peca pecaCapturada) {
        PecaXadrez p = (PecaXadrez) tabuleiro.removePeca(destino);
        p.decrementarQuantMovimento();
        tabuleiro.alocarPeca(p, origem);

        if (pecaCapturada != null) {
            tabuleiro.alocarPeca(pecaCapturada, destino);
            pecasCapturadas.remove(pecaCapturada);
            pecasNoTabuleiro.add(pecaCapturada);
        }

        // #Movimento especial Roque pequeno;
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() + 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() + 3);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() + 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(destinoT);
            tabuleiro.alocarPeca(torre, origemT);
            torre.decrementarQuantMovimento();
        }

        // #Movimento especial Roque grande;
        if (p instanceof Rei && destino.getColuna() == origem.getColuna() - 2) {
            Posicao origemT = new Posicao(origem.getLinha(), origem.getColuna() - 4);
            Posicao destinoT = new Posicao(origem.getLinha(), origem.getColuna() - 1);
            PecaXadrez torre = (PecaXadrez) tabuleiro.removePeca(destinoT);
            tabuleiro.alocarPeca(torre, origemT);
            torre.decrementarQuantMovimento();
        }
        
        // #Movimento especial En Passant;
        if (p instanceof Peao) {
            if (origem.getColuna() != destino.getColuna() && pecaCapturada == enPassantVulnerabilidade)  {
                PecaXadrez peao = (PecaXadrez)tabuleiro.removePeca(destino);
                 Posicao posicaoPeao;
                if (p.getCor() == Cor.BRANCA) {
                    posicaoPeao = new Posicao(3, destino.getColuna());
                } 
                else {
                    posicaoPeao = new Posicao(4, destino.getColuna());
                }
                tabuleiro.alocarPeca(peao, posicaoPeao);     
            }
        }    
        
    }

    private void validarPosicaoOrigem(Posicao posicao) {
        if (!tabuleiro.existeUmaPeca(posicao)) {
            throw new XadrezException("Não existe peça na posição de origem");
        }
        if (jogadorAtual != ((PecaXadrez) tabuleiro.peca(posicao)).getCor()) {
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
        List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : lista) {
            if (p instanceof Rei) {
                return (PecaXadrez) p;
            }
        }
        throw new IllegalStateException("Não existe rei " + cor + "no tabuleiro");
    }

    private boolean verificaXeque(Cor cor) {
        Posicao posicaoRei = rei(cor).getPosicaoXadrez().paraPosicaoLogica();
        List<Peca> pecaOponente = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == oponente(cor)).collect(Collectors.toList());
        for (Peca p : pecaOponente) {
            boolean[][] mat = p.movimentosPossiveis();
            if (mat[posicaoRei.getLinha()][posicaoRei.getColuna()]) {
                return true;
            }
        }
        return false;
    }

    private boolean verificaXequeMate(Cor cor) {
        if (!verificaXeque(cor)) {
            return false;
        }
        List<Peca> lista = pecasNoTabuleiro.stream().filter(x -> ((PecaXadrez) x).getCor() == cor).collect(Collectors.toList());
        for (Peca p : lista) {
            boolean[][] mat = p.movimentosPossiveis();
            for (int i = 0; i < tabuleiro.getLinhas(); i++) {
                for (int j = 0; j < tabuleiro.getColunas(); j++) {
                    if (mat[i][j]) {
                        Posicao origem = ((PecaXadrez) p).getPosicaoXadrez().paraPosicaoLogica();
                        Posicao destino = new Posicao(i, j);
                        Peca pecaCapturada = fazerMovimento(origem, destino);
                        boolean verificaXeque = verificaXeque(cor);
                        desfazerMovimento(origem, destino, pecaCapturada);
                        if (!verificaXeque) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void alocarNovaPeca(char coluna, int linha, PecaXadrez peca) {
        tabuleiro.alocarPeca(peca, new PosicaoXadrez(coluna, linha).paraPosicaoLogica());
        pecasNoTabuleiro.add(peca);
    }

    private void configuracaoInicial() {

        alocarNovaPeca('a', 1, new Torre(tabuleiro, Cor.BRANCA));
        alocarNovaPeca('b', 1, new Cavalo(tabuleiro, Cor.BRANCA));
        alocarNovaPeca('c', 1, new Bispo(tabuleiro, Cor.BRANCA));
        alocarNovaPeca('d', 1, new Rainha(tabuleiro, Cor.BRANCA));
        alocarNovaPeca('e', 1, new Rei(tabuleiro, Cor.BRANCA, this));
        alocarNovaPeca('f', 1, new Bispo(tabuleiro, Cor.BRANCA));
        alocarNovaPeca('g', 1, new Cavalo(tabuleiro, Cor.BRANCA));
        alocarNovaPeca('h', 1, new Torre(tabuleiro, Cor.BRANCA));
        alocarNovaPeca('a', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        alocarNovaPeca('b', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        alocarNovaPeca('c', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        alocarNovaPeca('d', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        alocarNovaPeca('e', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        alocarNovaPeca('f', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        alocarNovaPeca('g', 2, new Peao(tabuleiro, Cor.BRANCA, this));
        alocarNovaPeca('h', 2, new Peao(tabuleiro, Cor.BRANCA, this));

        alocarNovaPeca('a', 8, new Torre(tabuleiro, Cor.PRETA));
        alocarNovaPeca('b', 8, new Cavalo(tabuleiro, Cor.PRETA));
        alocarNovaPeca('c', 8, new Bispo(tabuleiro, Cor.PRETA));
        alocarNovaPeca('d', 8, new Rainha(tabuleiro, Cor.PRETA));
        alocarNovaPeca('e', 8, new Rei(tabuleiro, Cor.PRETA, this));
        alocarNovaPeca('f', 8, new Bispo(tabuleiro, Cor.PRETA));
        alocarNovaPeca('g', 8, new Cavalo(tabuleiro, Cor.PRETA));
        alocarNovaPeca('h', 8, new Torre(tabuleiro, Cor.PRETA));
        alocarNovaPeca('a', 7, new Peao(tabuleiro, Cor.PRETA, this));
        alocarNovaPeca('b', 7, new Peao(tabuleiro, Cor.PRETA, this));
        alocarNovaPeca('c', 7, new Peao(tabuleiro, Cor.PRETA, this));
        alocarNovaPeca('d', 7, new Peao(tabuleiro, Cor.PRETA, this));
        alocarNovaPeca('e', 7, new Peao(tabuleiro, Cor.PRETA, this));
        alocarNovaPeca('f', 7, new Peao(tabuleiro, Cor.PRETA, this));
        alocarNovaPeca('g', 7, new Peao(tabuleiro, Cor.PRETA, this));
        alocarNovaPeca('h', 7, new Peao(tabuleiro, Cor.PRETA, this));
    }
}

// @author Vinícius Trindade
package xadrez.pecas;

import tabuleirodojogo.Posicao;
import tabuleirodojogo.Tabuleiro;
import xadrez.Cor;
import xadrez.PecaXadrez;

public class Rei extends PecaXadrez {

    public Rei(Tabuleiro tabuleiro, Cor cor) {
        super(tabuleiro, cor);
    }
    
    @Override
    public String toString() {
        return "K";
    }
    
    private boolean podeMover(Posicao posicao) {
        PecaXadrez p = (PecaXadrez)getTabuleiro().peca(posicao);
        return p == null || p.getCor() != getCor();
    }

    @Override
    public boolean[][] movimentosPossiveis() {
        boolean mat[][] = new boolean[getTabuleiro().getLinhas()][getTabuleiro().getColunas()];
        
        Posicao p = new Posicao(0, 0);
        
        // Acima 
        p.setValores(posicao.getLinha() - 1, posicao.getColuna());
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        // Abaixo 
        p.setValores(posicao.getLinha() + 1, posicao.getColuna());
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        // Esquerda 
        p.setValores(posicao.getLinha() , posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
         // Direita 
        p.setValores(posicao.getLinha() , posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        // Noroeste 
        p.setValores(posicao.getLinha() - 1 , posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
         // Nordeste 
        p.setValores(posicao.getLinha() - 1 , posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        // Sudoeste 
        p.setValores(posicao.getLinha() + 1 , posicao.getColuna() - 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        // Sudeste 
        p.setValores(posicao.getLinha() + 1 , posicao.getColuna() + 1);
        if (getTabuleiro().posicaoExiste(p) && podeMover(p)) {
            mat[p.getLinha()][p.getColuna()] = true;
        }
        
        return mat;
    }
}

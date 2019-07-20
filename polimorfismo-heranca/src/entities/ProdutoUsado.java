// @author Vinícius Trindade
package entities;

import java.util.Date;
import java.text.SimpleDateFormat;

public class ProdutoUsado extends Produto {
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    private Date dataFabricacao;

    public ProdutoUsado() {
        super();
    }

    public ProdutoUsado(Date dataFabricacao, String nome, Double preco) {
        super(nome, preco);
        this.dataFabricacao = dataFabricacao;
    }

    public Date getDataFabricacao() {
        return dataFabricacao;
    }

    public void setDataFabricacao(Date dataFabricacao) {
        this.dataFabricacao = dataFabricacao;
    }

    @Override
    public final String etiquetaPreco() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getNome());
        sb.append("(usado)");
        sb.append(" R$ ");
        sb.append(String.format("%.2f", super.getPreco()));
        sb.append(" (Data de Fabricação: ");
        sb.append(sdf.format(dataFabricacao));
        sb.append(")");
        return sb.toString();
    }

}

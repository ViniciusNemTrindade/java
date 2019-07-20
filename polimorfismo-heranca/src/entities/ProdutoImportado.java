// @author Vinícius Trindade
package entities;

public class ProdutoImportado extends Produto {

    private Double taxaAlfandega;

    public ProdutoImportado() {
        super();
    }

    public ProdutoImportado(Double taxaAlfandega, String nome, Double preco) {
        super(nome, preco);
        this.taxaAlfandega = taxaAlfandega;
    }

    public Double getTaxaAlfandega() {
        return taxaAlfandega;
    }

    public void setTaxaAlfandega(Double taxaAlfandega) {
        this.taxaAlfandega = taxaAlfandega;
    }

    @Override
    public String etiquetaPreco() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.getNome());
        sb.append(" R$ ");
        sb.append(String.format("%.2f",precoTotal()));
        sb.append("( Taxa Alfandegaria: ");
        sb.append(" R$ ");
        sb.append(String.format("%.2f", taxaAlfandega));
        sb.append(")");
        return sb.toString();
    }

    public final Double precoTotal() {
        return super.getPreco() + taxaAlfandega;
    }
}

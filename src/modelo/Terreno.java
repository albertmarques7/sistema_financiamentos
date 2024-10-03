package modelo;

public class Terreno extends Financiamento {
    private final String zona;

    public Terreno(double valorDesejadoImovel, int prazoFinanciamento, double taxaJurosAnual, String zona) {
        super(valorDesejadoImovel, prazoFinanciamento, taxaJurosAnual);
        this.zona = zona;
    }

    public String getZona() {
        return zona;
    }

    @Override
    // Metodo para calcular o valor total a ser pago ao final do financiamento.

    public double calcularTotalPagamento() {
        int meses = getPrazoFinanciamento() * 12;
        return calcularValorPagamentoMensal() * meses;
    }

    public double calcularValorPagamentoMensal() {
        // Aplica um aumento de 2% sobre o cálculo.
        return super.calcularValorPagamentoMensal() * 1.02;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Valor do Imóvel: R$ %.2f%n", this.getValorImovel()));
        sb.append(String.format("Prazo de financiamento: %d anos%n", this.getPrazoFinanciamento()));
        sb.append(String.format("Taxa de juros anual: %.2f%%%n", this.getTaxaJurosAnual()));
        sb.append(String.format("Pagamento mensal: R$ %.2f%n", this.calcularValorPagamentoMensal()));
        sb.append(String.format("Valor do financiamento: R$ %.2f%n", this.calcularTotalPagamento()));
        sb.append(String.format("Tipo de zona: %s%n", this.getZona()));
        return sb.toString();
    }

}

package modelo;

/**
 * Classe abstrata que representa um financiamento de imóvel.
 *
 * Esta classe fornece a base para tipos específicos de imóveis (Casa, Apartamento, Terreno).
 * Ela armazena o valor do imóvel, o prazo do financiamento e a taxa de juros anual.
 * Métodos para calcular o valor das parcelas mensais e o valor total a ser pago são fornecidos.
 *
 * @version 1.0
 */
public abstract class Financiamento {

    /**
     * Valor do imóvel a ser financiado.
     */
    protected double valorImovel;

    /**
     * Prazo do financiamento em anos.
     */
    protected int prazoFinanciamento;

    /**
     * Taxa de juros anual aplicada ao financiamento.
     */
    protected double taxaJurosAnual;

    /**
     * Construtor da classe Financiamento.
     *
     * @param valorDesejadoImovel O valor do imóvel a ser financiado.
     * @param prazoFinanciamento O prazo do financiamento em anos.
     * @param taxaJurosAnual A taxa de juros anual aplicada ao financiamento.
     */
    public Financiamento(double valorDesejadoImovel, int prazoFinanciamento, double taxaJurosAnual) {
        this.valorImovel = valorDesejadoImovel;
        this.prazoFinanciamento = prazoFinanciamento;
        this.taxaJurosAnual = taxaJurosAnual;
    }

    /**
     * Retorna o valor do imóvel financiado.
     *
     * @return O valor do imóvel.
     */
    public double getValorImovel() {
        return this.valorImovel;
    }

    /**
     * Retorna o prazo do financiamento em anos.
     *
     * @return O prazo do financiamento.
     */
    public int getPrazoFinanciamento() {
        return this.prazoFinanciamento;
    }

    /**
     * Retorna a taxa de juros anual aplicada ao financiamento.
     *
     * @return A taxa de juros anual.
     */
    public double getTaxaJurosAnual() {
        return this.taxaJurosAnual;
    }

    /**
     * Calcula o valor mensal da parcela do financiamento.
     *
     * O cálculo considera o valor do imóvel dividido pelo número de meses,
     * aplicando a taxa de juros mensal.
     *
     * @return O valor mensal da parcela.
     */
    public double calcularValorPagamentoMensal() {
        return (this.getValorImovel() / (this.getPrazoFinanciamento() * 12)) * (1 + (this.getTaxaJurosAnual() / 12));
    }

    /**
     * Calcula o valor total a ser pago ao longo do financiamento.
     *
     * Multiplica o valor mensal calculado pelo número de meses do prazo total.
     *
     * @return O valor total a ser pago pelo financiamento.
     */
    public double calcularTotalPagamento() {
        return calcularValorPagamentoMensal() * this.getPrazoFinanciamento() * 12;
    }

    /**
     * Retorna uma representação em string do financiamento, contendo o valor do imóvel
     * e o valor total a ser pago.
     *
     * @return Uma string com o valor do imóvel e o total de pagamento.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Valor do Imóvel: R$ %.2f%n", this.getValorImovel()));
        sb.append(String.format("Total de Pagamento: R$ %.2f%n", this.calcularTotalPagamento()));
        return sb.toString();
    }
}
package modelo;

/**
 * Classe que representa um Apartamento no contexto de um financiamento.
 *
 * A classe herda de {@link Financiamento} e adiciona características específicas
 * como o número de vagas de garagem e o andar do apartamento.
 *
 * @version 1.0
 */
public class Apartamento extends Financiamento {

    /**
     * Quantidade de vagas na garagem do apartamento.
     */
    private final int vagasGaragem;

    /**
     * Andar em que o apartamento está localizado.
     */
    private final int andar;

    /**
     * Construtor da classe Apartamento.
     *
     * @param valorDesejadoImovel O valor do apartamento a ser financiado.
     * @param prazoFinanciamento O prazo do financiamento em anos.
     * @param taxaJurosAnual A taxa de juros anual aplicada ao financiamento.
     * @param vagasGaragem O número de vagas de garagem do apartamento.
     * @param andar O andar onde o apartamento está localizado.
     */
    public Apartamento(double valorDesejadoImovel, int prazoFinanciamento, double taxaJurosAnual, int vagasGaragem, int andar) {
        super(valorDesejadoImovel, prazoFinanciamento, taxaJurosAnual);
        this.vagasGaragem = vagasGaragem;
        this.andar = andar;
    }

    /**
     * Retorna o número de vagas de garagem do apartamento.
     *
     * @return O número de vagas de garagem.
     */
    public int getVagasGaragem() {
        return vagasGaragem;
    }

    /**
     * Retorna o andar em que o apartamento está localizado.
     *
     * @return O andar do apartamento.
     */
    public int getAndar() {
        return andar;
    }

    /**
     * Calcula o valor da parcela mensal do financiamento para o apartamento.
     *
     * O cálculo usa a fórmula do valor presente das parcelas com taxa de juros mensal composta.
     *
     * @return O valor mensal a ser pago pelo financiamento.
     */
    @Override
    public double calcularValorPagamentoMensal() {
        double taxaMensal = taxaJurosAnual / 12;
        int meses = prazoFinanciamento * 12;
        return valorImovel * (taxaMensal * Math.pow(1 + taxaMensal, meses)) / (Math.pow(1 + taxaMensal, meses) - 1);
    }

    /**
     * Retorna uma representação em string do financiamento do apartamento, incluindo
     * detalhes como valor do imóvel, taxa de juros, pagamento mensal, número de vagas de garagem e andar.
     *
     * @return Uma string detalhada sobre o financiamento do apartamento.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Valor do Imóvel: R$ %.2f%n", this.getValorImovel()));
        sb.append(String.format("Prazo de financiamento: %d anos%n", this.getPrazoFinanciamento()));
        sb.append(String.format("Taxa de juros anual: %.2f%%%n", this.getTaxaJurosAnual()));
        sb.append(String.format("Pagamento mensal: R$ %.2f%n", this.calcularValorPagamentoMensal()));
        sb.append(String.format("Valor do financiamento: R$ %.2f%n", this.calcularTotalPagamento()));
        sb.append(String.format("Quantidade de vagas de garagem: %d%n", this.getVagasGaragem()));
        sb.append(String.format("Andar: %d%n", this.getAndar()));
        return sb.toString();
    }
}

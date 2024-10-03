package modelo;

import util.AcrescimoMaiorDoQueJurosException;
import util.DescontoMaiorDoQueJurosException;

/**
 * A classe {@code Casa} representa um imóvel do tipo casa no contexto de um financiamento.
 * Além dos atributos comuns de um financiamento, ela armazena informações sobre a
 * área construída e a área do terreno. Também permite acréscimos e descontos no
 * valor do financiamento, respeitando algumas regras de validação.
 *
 * @version 1.0
 */
public class Casa extends Financiamento {

    private final double areaConstruida;
    private final double areaTerreno;
    private double valorAcrescimo;
    private double valorDesconto;

    /**
     * Construtor para a classe {@code Casa}.
     *
     * @param valorDesejadoImovel O valor do imóvel a ser financiado.
     * @param prazoFinanciamento O prazo do financiamento em anos.
     * @param taxaJurosAnual A taxa de juros anual aplicada ao financiamento.
     * @param areaConstruida A área construída da casa, em metros quadrados.
     * @param areaTerreno O tamanho do terreno da casa, em metros quadrados.
     */
    public Casa(double valorDesejadoImovel, int prazoFinanciamento, double taxaJurosAnual, double areaConstruida, double areaTerreno) {
        super(valorDesejadoImovel, prazoFinanciamento, taxaJurosAnual);
        this.areaConstruida = areaConstruida;
        this.areaTerreno = areaTerreno;
        this.valorAcrescimo = 0;
        this.valorDesconto = 0;
    }

    /**
     * Retorna a área construída da casa.
     *
     * @return A área construída da casa em metros quadrados.
     */
    public double getAreaConstruida() { return areaConstruida; }

    /**
     * Retorna a área do terreno da casa.
     *
     * @return A área do terreno em metros quadrados.
     */
    public double getAreaTerreno() { return areaTerreno; }

    /**
     * Retorna o valor do acréscimo aplicado no financiamento.
     *
     * @return O valor do acréscimo.
     */
    public double getValorAcrescimo() { return valorAcrescimo; }

    /**
     * Retorna o valor do desconto aplicado no financiamento.
     *
     * @return O valor do desconto.
     */
    public double getValorDesconto() { return valorDesconto; }

    /**
     * Valida se o acréscimo no financiamento não é maior do que o valor dos juros mensais.
     * Caso o acréscimo seja maior que os juros, uma exceção é lançada.
     *
     * @param valorJuros O valor dos juros mensais do financiamento.
     * @param valorAcrescimo O valor do acréscimo a ser validado.
     * @return {@code true} se o acréscimo for válido, {@code false} se o acréscimo for maior que os juros.
     */
    public boolean validarJuros(double valorJuros, double valorAcrescimo) {
        try {
            if (valorAcrescimo > valorJuros) {
                throw new AcrescimoMaiorDoQueJurosException(String.format(
                        "Acréscimo de R$%.2f é maior que o valor dos juros de R$%.2f", valorAcrescimo, valorJuros));
            }
            return true;
        } catch (AcrescimoMaiorDoQueJurosException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Valida se o desconto no financiamento não é maior do que o valor dos juros mensais.
     * Caso o desconto seja maior que os juros, uma exceção é lançada.
     *
     * @param valorJuros O valor dos juros mensais do financiamento.
     * @param valorDesconto O valor do desconto a ser validado.
     * @return {@code true} se o desconto for válido, {@code false} se o desconto for maior que os juros.
     */
    public boolean validarDesconto(double valorJuros, double valorDesconto) {
        try {
            if (valorDesconto > valorJuros) {
                throw new DescontoMaiorDoQueJurosException(String.format(
                        "Desconto de R$%.2f é maior que o valor dos juros de R$%.2f", valorDesconto, valorJuros));
            }
            return true;
        } catch (DescontoMaiorDoQueJurosException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    /**
     * Calcula o valor do pagamento mensal considerando os acréscimos e descontos
     * aplicados ao financiamento. Se o acréscimo ou desconto forem inválidos,
     * eles são ajustados para valores permitidos.
     *
     * @return O valor do pagamento mensal ajustado com acréscimos e descontos.
     */
    @Override
    public double calcularValorPagamentoMensal() {
        double valorMensalTotal = super.calcularValorPagamentoMensal();
        double taxaMensal = getTaxaJurosAnual() / 12 / 100;
        double saldoDevedor = getValorImovel();
        double jurosMensais = saldoDevedor * taxaMensal;

        // Definindo valores de acréscimo e desconto
        this.valorAcrescimo = 0.02 * saldoDevedor / getPrazoFinanciamento();
        this.valorDesconto = 0.01 * saldoDevedor / getPrazoFinanciamento();

        // Validação de acréscimo
        if (!validarJuros(jurosMensais, this.valorAcrescimo)) {
            this.valorAcrescimo = 0;
        }

        // Validação de desconto
        if (!validarDesconto(jurosMensais, this.valorDesconto)) {
            this.valorDesconto = jurosMensais;
        }

        return valorMensalTotal + this.valorAcrescimo - this.valorDesconto;
    }

    /**
     * Retorna uma representação textual da casa, incluindo valores do imóvel,
     * áreas, acréscimos, descontos e valores de pagamento.
     *
     * @return Uma string com os detalhes do financiamento da casa.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Valor do Imóvel: R$ %.2f%n", this.getValorImovel()));
        sb.append(String.format("Prazo de financiamento: %d anos%n", this.getPrazoFinanciamento()));
        sb.append(String.format("Taxa de juros anual: %.2f%%%n", this.getTaxaJurosAnual()));
        sb.append(String.format("Pagamento mensal: R$ %.2f%n", this.calcularValorPagamentoMensal()));
        sb.append(String.format("Valor do financiamento: R$ %.2f%n", this.calcularTotalPagamento()));
        sb.append(String.format("Valor do acréscimo por parcela: R$ %.2f%n", this.getValorAcrescimo()));
        sb.append(String.format("Valor do desconto por parcela: R$ %.2f%n", this.getValorDesconto()));
        sb.append(String.format("Área construída: %.2f m²%n", this.getAreaConstruida()));
        sb.append(String.format("Tamanho de terreno: %.2f m²%n", this.getAreaTerreno()));
        return sb.toString();
    }
}

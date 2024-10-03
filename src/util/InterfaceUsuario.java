package util;

import java.util.Scanner;

/**
 * A classe {@code InterfaceUsuario} gerencia a interação com o usuário para coletar
 * informações sobre financiamento imobiliário. Ela faz a validação das entradas
 * e retorna os dados necessários para a criação de um financiamento.
 *
 * @version 1.0
 */
public class InterfaceUsuario {
    Scanner scanner = new Scanner(System.in);

    /**
     * Solicita ao usuário o tipo de imóvel para o financiamento.
     *
     * @return O tipo de imóvel selecionado: "Apartamento", "Casa" ou "Terreno".
     */
    public String pedirTipoFinanciamento() {
        int tipo;
        String imovel = "";
        boolean valorInvalido = true;
        System.out.print("Selecione o imóvel 1 - Apartamento 2 - Casa 3- Terreno: ");
        do {
            try {
                tipo = scanner.nextInt();
                if (tipo >= 1 && tipo <= 3) {
                    if (tipo == 1) {
                        imovel = "Apartamento";
                    }
                    if (tipo == 2) {
                        imovel = "Casa";
                    }
                    if (tipo == 3) {
                        imovel = "Terreno";
                    }
                    valorInvalido = false;
                } else {
                    System.out.print("Número inválido. Selecione 1 - Apartamento 2 - Casa 3- Terreno: ");
                }
            } catch (Exception e) {
                System.out.print("Entrada inválida. Por favor, insira um número válido 1 - Apartamento 2 - Casa 3- Terreno: ");
                scanner.next();
            }
        } while (valorInvalido);
        return imovel;
    }

    /**
     * Solicita ao usuário o valor do imóvel.
     *
     * @return O valor do imóvel em reais, que deve ser maior ou igual a R$ 100.000.
     */
    public double pedirValorImovel() {
        double valorImovel = 0;
        boolean valorInvalido = true;
        System.out.print("Valor do imóvel (R$): ");
        do {
            try {
                valorImovel = scanner.nextDouble();

                if (valorImovel >= 100000.00) {
                    valorInvalido = false;
                } else {
                    System.out.print("Valor do imóvel inválido. Digite um valor mínimo de R$ 100.000: ");
                }
            } catch (Exception e) {
                System.out.print("Entrada inválida. Por favor, insira um número válido: ");
                scanner.next();
            }
        } while (valorInvalido);
        return valorImovel;
    }

    /**
     * Solicita ao usuário o prazo do financiamento em anos.
     *
     * @return O prazo do financiamento em anos, que deve estar entre 5 e 35 anos.
     */
    public int pedirPrazoFinanciamento() {
        int prazo = 0;
        boolean valorInvalido;
        System.out.print("Prazo do financiamento em anos: ");
        do {
            try {
                prazo = scanner.nextInt();
                if (prazo >= 5 && prazo <= 35) {
                    valorInvalido = false;
                } else {
                    System.out.print("Digite prazo entre 5-35 anos: ");
                    valorInvalido = true;
                }
            } catch (Exception e) {
                System.out.print("Entrada inválida. Por favor, insira um número válido: ");
                scanner.next();
                valorInvalido = true;
            }
        } while (valorInvalido);
        return prazo;
    }

    /**
     * Solicita ao usuário a taxa de juros anual do financiamento.
     *
     * @return A taxa de juros anual, que deve estar entre 2% e 10%.
     */
    public double pedirTaxaJuros() {
        double taxa = 0;
        boolean valorInvalido;
        System.out.print("Taxa de juros anual (%): ");
        do {
            try {
                taxa = scanner.nextDouble();
                if (taxa >= 2 && taxa <= 10) {
                    valorInvalido = false;
                } else {
                    System.out.print("Valor de taxa inválida. A taxa deve estar entre 2% e 10% ao ano: ");
                    valorInvalido = true;
                }
            } catch (Exception e) {
                System.out.print("Entrada inválida. Por favor, insira um número válido: ");
                scanner.next();
                valorInvalido = true;
            }
        } while (valorInvalido);
        return taxa;
    }

    /**
     * Solicita ao usuário a área do terreno em metros quadrados.
     *
     * @return A área do terreno, que deve estar entre 50 m² e 10.000 m².
     */
    public double pedirAreaTerreno() {
        double areaTerreno = 0;
        boolean valorInvalido;
        System.out.print("Área do terreno metros quadrados (m²): ");
        do {
            try {
                areaTerreno = scanner.nextDouble();
                if (areaTerreno >= 50.00 && areaTerreno <= 10000.00) {
                    valorInvalido = false;
                } else {
                    System.out.print("Digite uma área entre 50 e 10.000 m²: ");
                    valorInvalido = true;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, insira um número válido: ");
                scanner.next();
                valorInvalido = true;
            }
        } while (valorInvalido);
        return areaTerreno;
    }

    /**
     * Solicita ao usuário a área construída da casa.
     *
     * @return A área construída, que deve ser maior que 20 m².
     */
    public double pedirAreaConstruida() {
        double areaConstruida = 0;
        boolean valorInvalido;
        System.out.print("Área Construída: ");
        do {
            try {
                areaConstruida = scanner.nextDouble();
                if (areaConstruida > 20) {
                    valorInvalido = false;
                } else {
                    System.out.print("Digite área construída maior que 20 metros quadrados: ");
                    valorInvalido = true;
                }
            } catch (Exception e) {
                System.out.print("Entrada inválida. Por favor, insira um número válido: ");
                scanner.next();
                valorInvalido = true;
            }
        } while (valorInvalido);
        return areaConstruida;
    }

    /**
     * Solicita ao usuário o tipo de zona para terrenos.
     *
     * @return "Residencial" ou "Comercial", dependendo da escolha do usuário.
     */
    public String pedirZona() {
        int zona;
        String zonaString = "";
        boolean valorValido = false;
        do {
            try {
                System.out.print("Zona: 1 - Residencial \n2 - Comercial\nEscolha a zona: ");
                zona = scanner.nextInt();
                if (zona == 1) {
                    zonaString = "Residencial";
                    valorValido = true;
                } else if (zona == 2) {
                    zonaString = "Comercial";
                    valorValido = true;
                } else {
                    System.out.print("Opção inválida. Escolha 1 para Residencial ou 2 para Comercial: ");
                }
            } catch (Exception e) {
                System.out.print("Entrada inválida. Por favor, insira um número válido (1 ou 2): ");
                scanner.next();
            }
        } while (!valorValido);

        return zonaString;
    }

    /**
     * Solicita ao usuário o andar do apartamento.
     *
     * @return O andar informado pelo usuário.
     */
    public int pedirAndar() {
        int andar = 0;
        boolean valorValido = false;

        do {
            try {
                System.out.print("Andar: ");
                andar = scanner.nextInt();
                valorValido = true;
            } catch (Exception e) {
                System.out.print("Entrada inválida. Por favor, insira um número inteiro válido: ");
                scanner.next();
            }
        } while (!valorValido);

        return andar;
    }

    /**
     * Solicita ao usuário a quantidade de vagas de garagem.
     *
     * @return O número de vagas de garagem informado pelo usuário.
     */
    public int pedirGaragem() {
        int garagem = 0;
        boolean valorValido = false;
        do {
            try {
                System.out.print("Vaga(s) de garagem: ");
                garagem = scanner.nextInt();
                valorValido = true;
            } catch (Exception e) {
                System.out.print("Entrada inválida. Por favor, insira um número inteiro válido: ");
                scanner.next();
            }
        } while (!valorValido);

        return garagem;
    }
}

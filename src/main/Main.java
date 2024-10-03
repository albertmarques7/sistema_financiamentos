package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import modelo.Financiamento;
import modelo.Casa;
import modelo.Apartamento;
import modelo.Terreno;

import util.InterfaceUsuario;

import java.util.Objects;
import java.util.Scanner;

/**
 * Classe principal que executa o sistema de financiamento de imóveis.
 * O sistema permite ao usuário inserir informações sobre apartamentos, casas ou terrenos,
 * calcular o valor total de financiamentos e salvar esses dados em um arquivo.
 * <p>O usuário pode adicionar imóveis, calcular o total de financiamentos e imóveis,
 * além de optar por parar ou continuar a adição de novos financiamentos.
 * <p>O sistema também gera um arquivo de texto com os detalhes dos financiamentos
 * e permite a leitura desse arquivo na saída padrão.
 * @author Alberto Carlos de Oliveira Marques
 * @version 1.0
 */

public class Main {

    /**
     * Método principal do sistema de financiamento de imóveis.

     * Executa o fluxo principal que inclui a solicitação de dados de financiamento,
     * o cálculo de valores totais e a escrita dos dados em um arquivo.

     * @param args Argumentos da linha de comando (não utilizados neste sistema).
     */

    public static void main(String[] args) {
        int parar;
        int i;
        int contadorTerreno = 1; // Contador para terrenos
        int contadorCasa = 1; // Contador para casas
        int contadorApartamento = 1; // Contador para apartamentos
        List<Financiamento> listadeFinanciamentos = new ArrayList<>(); // Lista para armazenar os financiamentos
        String[] totais = new String[2]; // Array para armazenar o total de imóveis e financiamentos
        Scanner scanner = new Scanner(System.in);
        InterfaceUsuario interfaceUsuario1 = new InterfaceUsuario();

        String stringTotalImoveis;
        String stringTotalFinanciamentos;

        do {
            // Coleta os dados do tipo de imóvel e parâmetros de financiamento
            String tipoImovel = interfaceUsuario1.pedirTipoFinanciamento();
            double taxaJuros = interfaceUsuario1.pedirTaxaJuros();
            int prazoFinanciamentoEmAnos = interfaceUsuario1.pedirPrazoFinanciamento();
            double valorImovel = interfaceUsuario1.pedirValorImovel();

            // Lógica para adicionar um Apartamento, Casa ou Terreno
            if (Objects.equals(tipoImovel, "Apartamento")) {
                int vagasGaragem = interfaceUsuario1.pedirGaragem();
                int andar = interfaceUsuario1.pedirAndar();
                Apartamento apartamento = new Apartamento(valorImovel, prazoFinanciamentoEmAnos, taxaJuros, vagasGaragem, andar);
                listadeFinanciamentos.add(apartamento);
                contadorApartamento++;
            } else if (Objects.equals(tipoImovel, "Casa")) {
                double areaConstruida = interfaceUsuario1.pedirAreaConstruida();
                double areaTerreno = interfaceUsuario1.pedirAreaTerreno();
                Casa casa = new Casa(valorImovel, prazoFinanciamentoEmAnos, taxaJuros, areaConstruida, areaTerreno);
                listadeFinanciamentos.add(casa);
                contadorCasa++;
            } else if (Objects.equals(tipoImovel, "Terreno")) {
                String zona = interfaceUsuario1.pedirZona();
                Terreno terreno = new Terreno(valorImovel, prazoFinanciamentoEmAnos, taxaJuros, zona);
                listadeFinanciamentos.add(terreno);
                contadorTerreno++;
            }

            // Cálculo dos valores totais
            double valorTotalDeImoveisLista = 0;
            double valorTotalDosFinanciamentos = 0;
            System.out.println("Lista de Financiamentos:");

            // Itera pela lista de financiamentos e imprime os detalhes
            for (i = 0; i < listadeFinanciamentos.size(); i++) {
                Financiamento financiamento = listadeFinanciamentos.get(i);
                valorTotalDeImoveisLista += financiamento.getValorImovel();
                valorTotalDosFinanciamentos += financiamento.calcularTotalPagamento();
                System.out.printf("Financiamento %d - Imóvel: %s Valor do imóvel: R$ %.2f, Valor do financiamento: R$ %.2f\n",
                        i + 1, tipoImovel, financiamento.getValorImovel(), financiamento.calcularTotalPagamento());
            }
            System.out.printf("Valor total de imóveis: R$ %.2f\n", valorTotalDeImoveisLista);
            System.out.printf("Valor total de financiamentos: R$ %.2f\n", valorTotalDosFinanciamentos);

            // Pergunta ao usuário se ele quer parar ou continuar
            System.out.println("Você quer parar o programa?\n 1 - SIM \n 2 - NÃO");
            stringTotalImoveis = String.format("Valor total de imóveis: R$ %.2f\n", valorTotalDeImoveisLista);
            stringTotalFinanciamentos = String.format("Valor total de financiamentos: R$ %.2f\n", valorTotalDosFinanciamentos);
            parar = scanner.nextInt();
        } while (parar == 2);

        totais[0] = stringTotalImoveis;
        totais[1] = stringTotalFinanciamentos;
        scanner.close();

        // Escreve os dados no arquivo "Financiamentos.txt"
        FileWriter escrever = null;
        try {
            escrever = new FileWriter("Financiamentos.txt");
            for (Financiamento financiamento : listadeFinanciamentos) {
                escrever.write(financiamento.toString() + "\n");
            }
            for (String titulo : totais) {
                escrever.write(titulo);
            }
            escrever.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        lerCaracteres();
    }

    /**
     * Lê e exibe o conteúdo do arquivo "Financiamentos.txt". Este método lê o arquivo caractere por caractere
     * e imprime o seu conteúdo na saída padrão.
     */
    public static void lerCaracteres() {
        FileReader ler = null;
        try (FileReader in = new FileReader("Financiamentos.txt")) {
            int c;
            while ((c = in.read()) != -1) {
                System.out.print((char) c);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

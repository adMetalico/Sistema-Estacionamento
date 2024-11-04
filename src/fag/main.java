package fag;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        estacionamento estacionamento = new estacionamento();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        estacionamento.adicionarVaga(1, "pequeno");
        estacionamento.adicionarVaga(2, "medio");
        estacionamento.adicionarVaga(3, "grande");

        do {
            System.out.println("Sistema de Gerenciamento de Estacionamento");
            System.out.println("1. Registrar entrada de veículo");
            System.out.println("2. Registrar saída de veículo");
            System.out.println("3. Gerar relatório de vagas ocupadas");
            System.out.println("4. Gerar histórico de veículos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consome a nova linha

            switch (opcao) {
                case 1:
                    System.out.print("Placa do veículo: ");
                    String placaEntrada = scanner.nextLine();
                    System.out.print("Modelo do veículo: ");
                    String modeloEntrada = scanner.nextLine();
                    System.out.print("Tamanho do veículo (pequeno, medio, grande): ");
                    String tamanhoEntrada = scanner.nextLine();
                    estacionamento.registrarEntrada(placaEntrada, modeloEntrada, tamanhoEntrada);
                    break;

                case 2:
                    System.out.print("Placa do veículo: ");
                    String placaSaida = scanner.nextLine();
                    estacionamento.registrarSaida(placaSaida);
                    break;

                case 3:
                    estacionamento.gerarRelatorioVagasOcupadas();
                    break;

                case 4:
                    estacionamento.gerarHistoricoVeiculos();
                    break;

                case 5:
                    System.out.println("Encerrando o sistema.");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
            System.out.println();
        } while (opcao != 5);

        scanner.close();
    }
}
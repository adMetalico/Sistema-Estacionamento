package fag;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class estacionamento {
    private List<vaga> vagas;
    private List<registro> registros;

    public estacionamento() {
        vagas = new ArrayList<>();
        registros = new ArrayList<>();
    }

    public void adicionarVaga(int numero, String tamanho) {
        vagas.add(new vaga(numero, tamanho));
    }

    public void registrarEntrada(String placa, String modelo, String tamanho) {
        veiculo veiculo = new veiculo(placa, modelo, tamanho);
        vaga vagaDisponivel = encontrarVagaDisponivel(tamanho);

        if (vagaDisponivel != null) {
            veiculo.setHoraEntrada(LocalDateTime.now());
            vagaDisponivel.ocuparVaga();
            registros.add(new registro(veiculo, vagaDisponivel));
            System.out.println("Veículo " + placa + " estacionado na vaga " + vagaDisponivel.getNumero());
        } else {
            System.out.println("Nenhuma vaga disponível para o tamanho " + tamanho);
        }
    }

    public void registrarSaida(String placa) {
        registro registro = encontrarRegistroPorPlaca(placa);

        if (registro != null) {
            registro.registrarSaida(LocalDateTime.now());
            System.out.println("Veículo " + placa + " saiu. Valor pago: R$ " + registro.getValorPago());
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }

    private vaga encontrarVagaDisponivel(String tamanho) {
        for (vaga vaga : vagas) {
            if (vaga.isDisponivel() && vaga.getTamanho().equalsIgnoreCase(tamanho)) {
                return vaga;
            }
        }
        return null;
    }

    private registro encontrarRegistroPorPlaca(String placa) {
        for (registro registro : registros) {
            if (registro.getPlacaVeiculo().equalsIgnoreCase(placa) && registro.getHoraSaida() == null) {
                return registro;
            }
        }
        return null;
    }

    public void gerarRelatorioVagasOcupadas() {
        System.out.println("Vagas Ocupadas:");
        for (registro registro : registros) {
            if (registro.getHoraSaida() == null) {
                vaga vaga = registro.vaga;
                System.out.println("Vaga " + vaga.getNumero() + " - " + vaga.getTamanho() + " - Veículo: " + registro.getPlacaVeiculo());
            }
        }
    }

    public void gerarHistoricoVeiculos() {
        System.out.println("Histórico de Permanência de Veículos:");
        for (registro registro : registros) {
            System.out.println("----------------------------------");
            System.out.println("Veículo: " + registro.getPlacaVeiculo());
            System.out.println("Entrada: " + registro.getHoraEntrada());
            System.out.println("Saída: " + registro.getHoraSaida());
            System.out.println("Valor Pago: R$ " + registro.getValorPago());
            System.out.println("----------------------------------");
        }
    }
}
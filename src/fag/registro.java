package fag;

import java.time.Duration;
import java.time.LocalDateTime;

public class registro {
    private veiculo veiculo;
    public vaga vaga;
    private LocalDateTime horaEntrada;
    private LocalDateTime horaSaida;
    private double valorPago;

    public registro(veiculo veiculo, vaga vaga) {
        this.veiculo = veiculo;
        this.vaga = vaga;
        this.horaEntrada = veiculo.getHoraEntrada();
    }

    public void registrarSaida(LocalDateTime horaSaida) {
        this.horaSaida = horaSaida;
        veiculo.setHoraSaida(horaSaida);
        calcularValorPago();
        vaga.liberarVaga();
    }

    private void calcularValorPago() {
        Duration duracao = Duration.between(horaEntrada, horaSaida);
        long horas = duracao.toHours();

        if (horas <= 1) {
            valorPago = 5.0;
        } else if (horas <= 3) {
            valorPago = 10.0;
        } else {
            valorPago = 15.0;
        }
    }

    public String getPlacaVeiculo() {
        return veiculo.getPlaca();
    }

    public double getValorPago() {
        return valorPago;
    }

    public LocalDateTime getHoraEntrada() {
        return horaEntrada;
    }

    public LocalDateTime getHoraSaida() {
        return horaSaida;
    }
}
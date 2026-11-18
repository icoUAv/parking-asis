package pt.ua.asis.estacionamento.dominio;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Período de uma reserva, no intervalo [início, fim[ (o fim não está incluído).
 * Regra BR-01: o fim é posterior ao início e a duração não excede 12 horas.
 */
public record Periodo(LocalDateTime inicio, LocalDateTime fim) {

    public static final Duration DURACAO_MAXIMA = Duration.ofHours(12);

    public Periodo {
        if (inicio == null || fim == null) {
            throw new RegraNegocioException("BR-01", "O início e o fim são obrigatórios.");
        }
        if (!fim.isAfter(inicio)) {
            throw new RegraNegocioException("BR-01", "O fim tem de ser posterior ao início.");
        }
        if (Duration.between(inicio, fim).compareTo(DURACAO_MAXIMA) > 0) {
            throw new RegraNegocioException("BR-01", "Uma reserva não pode exceder 12 horas.");
        }
    }

    /** Converte datas no formato ISO (ex.: 2026-12-02T10:00), como chegam do formulário e da API. */
    public static Periodo de(String inicio, String fim) {
        try {
            return new Periodo(LocalDateTime.parse(inicio), LocalDateTime.parse(fim));
        } catch (DateTimeParseException | NullPointerException e) {
            throw new RegraNegocioException("BR-01", "Datas inválidas: use o formato AAAA-MM-DDThh:mm.");
        }
    }

    /** Dois períodos sobrepõem-se se um começa antes de o outro acabar (fim exclusivo). */
    public boolean sobrepoe(Periodo outro) {
        return inicio.isBefore(outro.fim) && outro.inicio.isBefore(fim);
    }

    /** O instante pertence ao período [início, fim[. */
    public boolean contem(LocalDateTime instante) {
        return !instante.isBefore(inicio) && instante.isBefore(fim);
    }
}

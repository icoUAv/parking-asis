package pt.ua.asis.estacionamento.dominio;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** Testes unitários das regras do período (BR-01) e da sobreposição usada na lotação. */
class PeriodoTest {

    private static Periodo p(String inicio, String fim) {
        return Periodo.de(inicio, fim);
    }

    @Test
    @DisplayName("BR-01 fim igual ao início é recusado")
    void fimIgualAoInicio() {
        assertThatThrownBy(() -> p("2026-12-02T10:00", "2026-12-02T10:00"))
                .isInstanceOf(RegraNegocioException.class)
                .extracting("regra").isEqualTo("BR-01");
    }

    @Test
    @DisplayName("BR-01 duração de exatamente 12 horas é aceite (fronteira)")
    void dozeHorasExatas() {
        assertThat(p("2026-12-02T08:00", "2026-12-02T20:00").fim()).isNotNull();
    }

    @Test
    @DisplayName("BR-01 duração de 12 horas e 1 minuto é recusada (fronteira)")
    void dozeHorasEUmMinuto() {
        assertThatThrownBy(() -> p("2026-12-02T08:00", "2026-12-02T20:01"))
                .isInstanceOf(RegraNegocioException.class)
                .extracting("regra").isEqualTo("BR-01");
    }

    @Test
    @DisplayName("Períodos que se intersetam sobrepõem-se")
    void sobreposicaoParcial() {
        assertThat(p("2026-12-02T10:00", "2026-12-02T12:00")
                .sobrepoe(p("2026-12-02T11:00", "2026-12-02T13:00"))).isTrue();
    }

    @Test
    @DisplayName("Períodos consecutivos não se sobrepõem: o fim não está incluído (fronteira)")
    void periodosConsecutivos() {
        assertThat(p("2026-12-02T10:00", "2026-12-02T12:00")
                .sobrepoe(p("2026-12-02T12:00", "2026-12-02T14:00"))).isFalse();
    }
}

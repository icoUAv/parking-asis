package pt.ua.asis.estacionamento.dominio;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/** Testes unitários da regra de lotação (BR-02). */
class LotacaoTest {

    @Test
    @DisplayName("BR-02 com 1 de 2 lugares ocupados há lugar")
    void haLugar() {
        assertThat(Lotacao.haLugar(2, 1)).isTrue();
    }

    @Test
    @DisplayName("BR-02 com 2 de 2 lugares ocupados não há lugar (fronteira)")
    void lotacaoEsgotada() {
        assertThat(Lotacao.haLugar(2, 2)).isFalse();
    }
}

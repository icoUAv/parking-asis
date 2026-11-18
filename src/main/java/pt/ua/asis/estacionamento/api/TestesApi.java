package pt.ua.asis.estacionamento.api;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.asis.estacionamento.dominio.ReservaRepository;

/**
 * Reposição dos dados antes de cada teste de ponta a ponta.
 * Só existe com o perfil "e2e": nunca fica ativo em produção.
 */
@RestController
@Profile("e2e")
public class TestesApi {

    private final ReservaRepository reservas;

    public TestesApi(ReservaRepository reservas) {
        this.reservas = reservas;
    }

    @PostMapping("/api/testes/reset")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void reset() {
        reservas.deleteAll();
    }
}

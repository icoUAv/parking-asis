package pt.ua.asis.estacionamento.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.asis.estacionamento.servico.AcessoService;

/** UC-04 Registar entrada no parque (chamado pelo leitor de matrículas). */
@RestController
public class AcessoApi {

    public record LeituraEntrada(Long parqueId, String matricula, String instante) {
    }

    public record Decisao(String decisao) {
    }

    private final AcessoService acessos;

    public AcessoApi(AcessoService acessos) {
        this.acessos = acessos;
    }

    @PostMapping("/api/acessos/entradas")
    public ResponseEntity<Decisao> registarEntrada(@RequestBody LeituraEntrada leitura) {
        boolean autorizada = acessos.autorizarEntrada(
                leitura.parqueId(), leitura.matricula(), leitura.instante());
        return autorizada
                ? ResponseEntity.ok(new Decisao("AUTORIZADA"))
                : ResponseEntity.status(HttpStatus.FORBIDDEN).body(new Decisao("RECUSADA"));
    }
}

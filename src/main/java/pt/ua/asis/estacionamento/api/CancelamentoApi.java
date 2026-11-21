package pt.ua.asis.estacionamento.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.asis.estacionamento.servico.CancelamentoService;

/** UC-03 Cancelar reserva. */
@RestController
public class CancelamentoApi {

    private final CancelamentoService cancelamento;

    public CancelamentoApi(CancelamentoService cancelamento) {
        this.cancelamento = cancelamento;
    }

    @PostMapping("/api/reservas/{id}/cancelamento")
    public ReservaDto cancelar(@PathVariable("id") Long id) {
        return ReservaDto.de(cancelamento.cancelar(id));
    }
}

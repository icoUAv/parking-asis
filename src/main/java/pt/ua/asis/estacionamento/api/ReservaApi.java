package pt.ua.asis.estacionamento.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.asis.estacionamento.servico.ReservaService;

/** UC-02 Reservar lugar. */
@RestController
@RequestMapping("/api/reservas")
public class ReservaApi {

    public record PedidoReserva(Long parqueId, String matricula, String inicio, String fim) {
    }

    private final ReservaService reservas;

    public ReservaApi(ReservaService reservas) {
        this.reservas = reservas;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservaDto reservar(@RequestBody PedidoReserva pedido) {
        return ReservaDto.de(reservas.reservar(
                pedido.parqueId(), pedido.matricula(), pedido.inicio(), pedido.fim()));
    }
}

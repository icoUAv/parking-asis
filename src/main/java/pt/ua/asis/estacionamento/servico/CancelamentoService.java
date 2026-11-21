package pt.ua.asis.estacionamento.servico;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.ua.asis.estacionamento.dominio.NaoEncontradoException;
import pt.ua.asis.estacionamento.dominio.Reserva;
import pt.ua.asis.estacionamento.dominio.ReservaRepository;

/** UC-03 Cancelar reserva. */
@Service
public class CancelamentoService {

    private final ReservaRepository reservas;

    public CancelamentoService(ReservaRepository reservas) {
        this.reservas = reservas;
    }

    @Transactional
    public Reserva cancelar(Long reservaId) {
        Reserva reserva = reservas.findById(reservaId)
                .orElseThrow(() -> new NaoEncontradoException("Reserva inexistente: " + reservaId));
        reserva.cancelar();
        return reserva;
    }
}

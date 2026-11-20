package pt.ua.asis.estacionamento.servico;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.ua.asis.estacionamento.dominio.EstadoReserva;
import pt.ua.asis.estacionamento.dominio.NaoEncontradoException;
import pt.ua.asis.estacionamento.dominio.Parque;
import pt.ua.asis.estacionamento.dominio.ParqueRepository;
import pt.ua.asis.estacionamento.dominio.Periodo;
import pt.ua.asis.estacionamento.dominio.ReservaRepository;

/** UC-01 Consultar disponibilidade. */
@Service
public class DisponibilidadeService {

    private final ParqueRepository parques;
    private final ReservaRepository reservas;

    public DisponibilidadeService(ParqueRepository parques, ReservaRepository reservas) {
        this.parques = parques;
        this.reservas = reservas;
    }

    @Transactional(readOnly = true)
    public Disponibilidade consultar(Long parqueId, String inicio, String fim) {
        Parque parque = parques.findById(parqueId)
                .orElseThrow(() -> new NaoEncontradoException("Parque inexistente: " + parqueId));
        Periodo periodo = Periodo.de(inicio, fim);
        long ocupados = reservas.contarSobrepostas(
                parqueId, EstadoReserva.ATIVA, periodo.inicio(), periodo.fim());
        long livres = Math.max(0, parque.getLotacao() - ocupados);
        return new Disponibilidade(parque.getId(), parque.getNome(), parque.getLotacao(), livres);
    }

    public record Disponibilidade(Long parqueId, String parque, int lotacao, long livres) {
    }
}

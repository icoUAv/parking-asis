package pt.ua.asis.estacionamento.servico;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.ua.asis.estacionamento.dominio.EstadoReserva;
import pt.ua.asis.estacionamento.dominio.Lotacao;
import pt.ua.asis.estacionamento.dominio.NaoEncontradoException;
import pt.ua.asis.estacionamento.dominio.Parque;
import pt.ua.asis.estacionamento.dominio.ParqueRepository;
import pt.ua.asis.estacionamento.dominio.Periodo;
import pt.ua.asis.estacionamento.dominio.RegraNegocioException;
import pt.ua.asis.estacionamento.dominio.Reserva;
import pt.ua.asis.estacionamento.dominio.ReservaRepository;

/** UC-02 Reservar lugar. */
@Service
public class ReservaService {

    private final ParqueRepository parques;
    private final ReservaRepository reservas;

    public ReservaService(ParqueRepository parques, ReservaRepository reservas) {
        this.parques = parques;
        this.reservas = reservas;
    }

    @Transactional
    public Reserva reservar(Long parqueId, String matricula, String inicio, String fim) {
        Parque parque = parques.findById(parqueId)
                .orElseThrow(() -> new NaoEncontradoException("Parque inexistente: " + parqueId));
        Periodo periodo = Periodo.de(inicio, fim);

        // BR-02: há lugar se as reservas ativas sobrepostas forem menos do que a lotação
        long ocupados = reservas.contarSobrepostas(
                parqueId, EstadoReserva.ATIVA, periodo.inicio(), periodo.fim());
        if (!Lotacao.haLugar(parque.getLotacao(), ocupados)) {
            throw new RegraNegocioException("BR-02", "Não há lugares disponíveis no período pedido.");
        }

        return reservas.save(new Reserva(parque, normalizar(matricula), periodo));
    }

    static String normalizar(String matricula) {
        return matricula == null ? "" : matricula.trim().toUpperCase();
    }
}

package pt.ua.asis.estacionamento.servico;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.ua.asis.estacionamento.dominio.NaoEncontradoException;
import pt.ua.asis.estacionamento.dominio.Parque;
import pt.ua.asis.estacionamento.dominio.ParqueRepository;
import pt.ua.asis.estacionamento.dominio.Periodo;
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
        return reservas.save(new Reserva(parque, normalizar(matricula), periodo));
    }

    static String normalizar(String matricula) {
        return matricula == null ? "" : matricula.trim().toUpperCase();
    }
}

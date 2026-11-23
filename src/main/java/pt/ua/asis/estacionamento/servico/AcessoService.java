package pt.ua.asis.estacionamento.servico;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pt.ua.asis.estacionamento.dominio.EstadoReserva;
import pt.ua.asis.estacionamento.dominio.NaoEncontradoException;
import pt.ua.asis.estacionamento.dominio.ParqueRepository;
import pt.ua.asis.estacionamento.dominio.ReservaRepository;

/**
 * UC-04 Registar entrada no parque.
 * Chamado pelo leitor de matrículas à entrada (simulado nos testes).
 * BR-04: a entrada é autorizada se a matrícula tiver uma reserva ativa nesse parque
 * cujo período inclui o instante da leitura.
 */
@Service
public class AcessoService {

    private final ParqueRepository parques;
    private final ReservaRepository reservas;

    public AcessoService(ParqueRepository parques, ReservaRepository reservas) {
        this.parques = parques;
        this.reservas = reservas;
    }

    @Transactional(readOnly = true)
    public boolean autorizarEntrada(Long parqueId, String matricula, String instante) {
        if (!parques.existsById(parqueId)) {
            throw new NaoEncontradoException("Parque inexistente: " + parqueId);
        }
        LocalDateTime momento;
        try {
            momento = LocalDateTime.parse(instante);
        } catch (DateTimeParseException | NullPointerException e) {
            throw new IllegalArgumentException("Instante inválido: use o formato AAAA-MM-DDThh:mm.");
        }
        return reservas.contarAtivasNoInstante(
                parqueId, ReservaService.normalizar(matricula), EstadoReserva.ATIVA, momento) > 0;
    }
}

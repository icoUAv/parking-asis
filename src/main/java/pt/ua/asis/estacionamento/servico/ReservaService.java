package pt.ua.asis.estacionamento.servico;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    private final JdbcTemplate jdbc;

    public ReservaService(ParqueRepository parques, ReservaRepository reservas, JdbcTemplate jdbc) {
        this.parques = parques;
        this.reservas = reservas;
        this.jdbc = jdbc;
    }

    @Transactional
    public Reserva reservar(Long parqueId, String matricula, String inicio, String fim) {
        Parque parque = parques.findById(parqueId)
                .orElseThrow(() -> new NaoEncontradoException("Parque inexistente: " + parqueId));

        // BR-02: verificar a lotação do parque no período pedido
        Long ocupados = jdbc.queryForObject(
                "SELECT COUNT(*) FROM reserva WHERE parque_id = " + parqueId
                        + " AND estado = 'ATIVA' AND inicio < '" + fim + "' AND fim > '" + inicio + "'",
                Long.class);
        if (!Lotacao.haLugar(parque.getLotacao(), ocupados)) {
            throw new RegraNegocioException("BR-02", "Não há lugares disponíveis no período pedido.");
        }

        Periodo periodo = Periodo.de(inicio, fim);
        return reservas.save(new Reserva(parque, normalizar(matricula), periodo));
    }

    static String normalizar(String matricula) {
        return matricula == null ? "" : matricula.trim().toUpperCase();
    }
}

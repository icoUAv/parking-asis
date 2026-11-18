package pt.ua.asis.estacionamento.dominio;

import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    /** Reservas ativas do parque que se sobrepõem a [inicio, fim[. */
    @Query("""
            select count(r) from Reserva r
            where r.parque.id = :parqueId and r.estado = :estado
              and r.inicio < :fim and r.fim > :inicio
            """)
    long contarSobrepostas(@Param("parqueId") Long parqueId,
                           @Param("estado") EstadoReserva estado,
                           @Param("inicio") LocalDateTime inicio,
                           @Param("fim") LocalDateTime fim);

    /** Reservas ativas da matrícula no parque que incluem o instante. */
    @Query("""
            select count(r) from Reserva r
            where r.parque.id = :parqueId and r.matricula = :matricula and r.estado = :estado
              and r.inicio <= :instante and r.fim > :instante
            """)
    long contarAtivasNoInstante(@Param("parqueId") Long parqueId,
                                @Param("matricula") String matricula,
                                @Param("estado") EstadoReserva estado,
                                @Param("instante") LocalDateTime instante);
}

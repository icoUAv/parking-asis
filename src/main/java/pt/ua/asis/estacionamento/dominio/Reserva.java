package pt.ua.asis.estacionamento.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Parque parque;

    private String matricula;

    private LocalDateTime inicio;

    private LocalDateTime fim;

    @Enumerated(EnumType.STRING)
    private EstadoReserva estado;

    protected Reserva() {
    }

    public Reserva(Parque parque, String matricula, Periodo periodo) {
        this.parque = parque;
        this.matricula = matricula;
        this.inicio = periodo.inicio();
        this.fim = periodo.fim();
        this.estado = EstadoReserva.ATIVA;
    }

    /** BR-03: só uma reserva ativa pode ser cancelada. */
    public void cancelar() {
        if (estado != EstadoReserva.ATIVA) {
            throw new RegraNegocioException("BR-03", "Só é possível cancelar uma reserva ativa.");
        }
        estado = EstadoReserva.CANCELADA;
    }

    public Periodo periodo() {
        return new Periodo(inicio, fim);
    }

    public Long getId() {
        return id;
    }

    public Parque getParque() {
        return parque;
    }

    public String getMatricula() {
        return matricula;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }

    public EstadoReserva getEstado() {
        return estado;
    }
}

package pt.ua.asis.estacionamento.api;

import pt.ua.asis.estacionamento.dominio.Reserva;

public record ReservaDto(Long id, Long parqueId, String matricula, String inicio, String fim, String estado) {

    public static ReservaDto de(Reserva r) {
        return new ReservaDto(r.getId(), r.getParque().getId(), r.getMatricula(),
                r.getInicio().toString(), r.getFim().toString(), r.getEstado().name());
    }
}

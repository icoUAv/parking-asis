package pt.ua.asis.estacionamento.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.ua.asis.estacionamento.dominio.ParqueRepository;
import pt.ua.asis.estacionamento.dominio.RegraNegocioException;
import pt.ua.asis.estacionamento.dominio.Reserva;
import pt.ua.asis.estacionamento.servico.ReservaService;

/** UC-02 Reservar lugar (interface web). */
@Controller
public class ReservaWeb {

    private final ParqueRepository parques;
    private final ReservaService reservas;

    public ReservaWeb(ParqueRepository parques, ReservaService reservas) {
        this.parques = parques;
        this.reservas = reservas;
    }

    @GetMapping("/reservas/nova")
    public String formulario(Model model) {
        model.addAttribute("parques", parques.findAll());
        return "reserva-form";
    }

    @PostMapping("/reservas")
    public String reservar(@RequestParam("parqueId") Long parqueId,
                           @RequestParam("matricula") String matricula,
                           @RequestParam("inicio") String inicio,
                           @RequestParam("fim") String fim,
                           Model model) {
        try {
            Reserva reserva = reservas.reservar(parqueId, matricula, inicio, fim);
            model.addAttribute("reserva", reserva);
            return "reserva-confirmada";
        } catch (RegraNegocioException e) {
            model.addAttribute("parques", parques.findAll());
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("parqueId", parqueId);
            model.addAttribute("matricula", matricula);
            model.addAttribute("inicio", inicio);
            model.addAttribute("fim", fim);
            return "reserva-form";
        }
    }
}

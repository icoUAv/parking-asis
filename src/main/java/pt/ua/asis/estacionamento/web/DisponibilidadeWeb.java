package pt.ua.asis.estacionamento.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pt.ua.asis.estacionamento.dominio.ParqueRepository;
import pt.ua.asis.estacionamento.dominio.RegraNegocioException;
import pt.ua.asis.estacionamento.servico.DisponibilidadeService;

/** UC-01 Consultar disponibilidade (interface web). */
@Controller
public class DisponibilidadeWeb {

    private final ParqueRepository parques;
    private final DisponibilidadeService disponibilidade;

    public DisponibilidadeWeb(ParqueRepository parques, DisponibilidadeService disponibilidade) {
        this.parques = parques;
        this.disponibilidade = disponibilidade;
    }

    @GetMapping("/disponibilidade")
    public String consultar(@RequestParam(name = "parqueId", required = false) Long parqueId,
                            @RequestParam(name = "inicio", required = false) String inicio,
                            @RequestParam(name = "fim", required = false) String fim,
                            Model model) {
        model.addAttribute("parques", parques.findAll());
        model.addAttribute("parqueId", parqueId);
        model.addAttribute("inicio", inicio);
        model.addAttribute("fim", fim);
        if (parqueId != null && inicio != null && fim != null) {
            try {
                model.addAttribute("resultado", disponibilidade.consultar(parqueId, inicio, fim));
            } catch (RegraNegocioException e) {
                model.addAttribute("erro", e.getMessage());
            }
        }
        return "disponibilidade";
    }
}

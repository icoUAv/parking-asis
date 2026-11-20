package pt.ua.asis.estacionamento.api;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pt.ua.asis.estacionamento.dominio.ParqueRepository;
import pt.ua.asis.estacionamento.servico.DisponibilidadeService;
import pt.ua.asis.estacionamento.servico.DisponibilidadeService.Disponibilidade;

@RestController
@RequestMapping("/api/parques")
public class ParqueApi {

    public record ParqueDto(Long id, String nome, int lotacao) {
    }

    private final ParqueRepository parques;
    private final DisponibilidadeService disponibilidade;

    public ParqueApi(ParqueRepository parques, DisponibilidadeService disponibilidade) {
        this.parques = parques;
        this.disponibilidade = disponibilidade;
    }

    @GetMapping
    public List<ParqueDto> listar() {
        return parques.findAll().stream()
                .map(p -> new ParqueDto(p.getId(), p.getNome(), p.getLotacao()))
                .toList();
    }

    /** UC-01 Consultar disponibilidade. */
    @GetMapping("/{id}/disponibilidade")
    public Disponibilidade disponibilidade(@PathVariable("id") Long id,
                                           @RequestParam("inicio") String inicio,
                                           @RequestParam("fim") String fim) {
        return disponibilidade.consultar(id, inicio, fim);
    }
}

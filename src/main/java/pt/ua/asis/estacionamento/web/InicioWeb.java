package pt.ua.asis.estacionamento.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioWeb {

    @GetMapping("/")
    public String inicio() {
        return "index";
    }
}

package pt.ua.asis.estacionamento.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pt.ua.asis.estacionamento.dominio.NaoEncontradoException;
import pt.ua.asis.estacionamento.dominio.RegraNegocioException;

/** Traduz as exceções do domínio em respostas da API. */
@RestControllerAdvice(basePackages = "pt.ua.asis.estacionamento.api")
public class ErrosApi {

    public record Erro(String regra, String mensagem) {
    }

    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_CONTENT)
    public Erro regra(RegraNegocioException e) {
        return new Erro(e.getRegra(), e.getMessage());
    }

    @ExceptionHandler(NaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Erro naoEncontrado(NaoEncontradoException e) {
        return new Erro(null, e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Erro pedidoInvalido(IllegalArgumentException e) {
        return new Erro(null, e.getMessage());
    }
}

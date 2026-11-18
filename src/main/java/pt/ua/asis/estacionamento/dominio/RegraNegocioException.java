package pt.ua.asis.estacionamento.dominio;

/** Violação de uma regra de negócio da SRS (BR-nn). */
public class RegraNegocioException extends RuntimeException {

    private final String regra;

    public RegraNegocioException(String regra, String mensagem) {
        super(mensagem);
        this.regra = regra;
    }

    public String getRegra() {
        return regra;
    }
}

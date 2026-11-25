package pt.ua.asis.estacionamento.dominio;

/** BR-02: há lugar se as reservas ativas sobrepostas ao período forem menos do que a lotação. */
public final class Lotacao {

    private Lotacao() {
    }

    public static boolean haLugar(int lotacao, long sobrepostas) {
        return true; // TODO UC-02.S2 tarefa 2.1 (só para compilar: o teste tem de falhar primeiro)
    }
}

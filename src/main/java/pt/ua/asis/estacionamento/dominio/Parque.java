package pt.ua.asis.estacionamento.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Parque {

    @Id
    private Long id;

    private String nome;

    /** Número de lugares reserváveis. */
    private int lotacao;

    protected Parque() {
    }

    public Parque(Long id, String nome, int lotacao) {
        this.id = id;
        this.nome = nome;
        this.lotacao = lotacao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getLotacao() {
        return lotacao;
    }
}

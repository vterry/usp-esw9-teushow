package br.com.usp.esw.teushow.vendas.module.entity;

import br.com.usp.esw.common.domain.entity.AggregateRoot;
import br.com.usp.esw.common.domain.valueobjects.IdEvento;

import java.util.List;

public class Evento extends AggregateRoot<IdEvento> {

    private final List<Ingresso> ingressos;
    private boolean ativo;

    private Evento(Builder builder) {
        super.setId(builder.idEvento);
        ingressos = builder.ingressos;
        ativo = builder.ativo;
    }

    public static Builder builder() {
        return new Builder();
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public static final class Builder {
        private IdEvento idEvento;
        private List<Ingresso> ingressos;
        private boolean ativo;

        private Builder() {
        }

        public Builder idEvento(IdEvento val) {
            idEvento = val;
            return this;
        }

        public Builder ingressos(List<Ingresso> val) {
            ingressos = val;
            return this;
        }

        public Builder ativo(boolean val) {
            ativo = val;
            return this;
        }

        public Evento build() {
            return new Evento(this);
        }
    }
}

package br.com.usp.esw.teushow.vendas.module.entity;

import br.com.usp.esw.common.domain.entity.BaseEntity;
import br.com.usp.esw.common.domain.valueobjects.IdIngresso;
import br.com.usp.esw.common.domain.valueobjects.Money;
import br.com.usp.esw.common.domain.valueobjects.TipoIngresso;

public class Ingresso extends BaseEntity<IdIngresso> {
    private Money preco;
    private TipoIngresso tipoIngresso;

    public Ingresso(IdIngresso idIngresso, Money preco, TipoIngresso tipoIngresso){
        super.setId(idIngresso);
        this.preco = preco;
        this.tipoIngresso = tipoIngresso;
    }

    public Ingresso(IdIngresso idIngresso) {
        super.setId(idIngresso);
    }

    public void atualizarIngresso(Money preco, TipoIngresso tipoIngresso){
        this.preco = preco;
        this.tipoIngresso = tipoIngresso;
    }

    public Money getPreco() {
        return preco;
    }

    public TipoIngresso getTipoIngresso() {
        return tipoIngresso;
    }
}

package br.com.usp.esw.teushow.vendas.module.entity;

import br.com.usp.esw.common.domain.entity.BaseEntity;
import br.com.usp.esw.common.domain.valueobjects.IdPedido;
import br.com.usp.esw.common.domain.valueobjects.Money;
import br.com.usp.esw.teushow.vendas.module.valueobject.IdItemPedido;

public class ItemPedido extends BaseEntity<IdItemPedido> {
    private IdPedido idPedido;

    private final Ingresso ingresso;
    private final int quantidade;
    private final Money preco;
    private final Money subTotal;


    void inicializarItemPedido(IdPedido idPedido, IdItemPedido idItemPedido){
        this.idPedido = idPedido;
        super.setId(idItemPedido);
    }

    boolean isPrecoValido(){
        return preco.isGreaterThanZero() &&
                preco.equals(ingresso.getPreco()) &&
                preco.multiply(quantidade).equals(subTotal);
    }

    private ItemPedido(Builder builder) {
        super.setId(builder.idItemPedido);
        ingresso = builder.ingresso;
        quantidade = builder.quantidade;
        preco = builder.preco;
        subTotal = builder.subTotal;
    }

    public static Builder builder() {
        return new Builder();
    }

    public IdPedido getIdPedido() {
        return idPedido;
    }

    public Ingresso getIngresso() {
        return ingresso;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public Money getPreco() {
        return preco;
    }

    public Money getSubTotal() {
        return subTotal;
    }

    public static final class Builder {
        private IdItemPedido idItemPedido;
        private Ingresso ingresso;
        private int quantidade;
        private Money preco;
        private Money subTotal;

        public Builder() {
        }

        public Builder idItemPedido(IdItemPedido val) {
            idItemPedido = val;
            return this;
        }


        public Builder ingresso(Ingresso val) {
            ingresso = val;
            return this;
        }

        public Builder quantidade(int val) {
            quantidade = val;
            return this;
        }

        public Builder preco(Money val) {
            preco = val;
            return this;
        }

        public Builder subTotal(Money val) {
            subTotal = val;
            return this;
        }

        public ItemPedido build() {
            return new ItemPedido(this);
        }
    }
}

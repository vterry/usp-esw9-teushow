package entity;

import valueobjects.IdEvento;
import valueobjects.IdItemPedido;
import valueobjects.IdPedido;
import valueobjects.Monetario;

import java.math.BigDecimal;

public class ItemPedido extends BaseEntity<IdItemPedido> {
    private IdPedido idPedido;
    private final IdEvento idEvento;
    private final int quantidade;
    private final Monetario valorUnitario;
    private final Monetario valorTotal;

    void inicializarItemPedido(IdPedido idPedido, IdItemPedido idItemPedido) {
        this.idPedido = idPedido;
        super.setId(idItemPedido);
    }

    //TODO - caso eu tivesse a informação de preço em outro CD, poderia chama-lo através dessa classe?
    boolean precoEhValido() {
        return valorUnitario.eMaiorQueZero() && valorUnitario.multiplicar(quantidade).equals(valorTotal);
    }


    private ItemPedido(Builder builder) {
        super.setId(builder.idItemPedido);
        idEvento = builder.idEvento;
        quantidade = builder.quantidade;
        valorUnitario = builder.valorUnitario;
        valorTotal = builder.valorTotal;
    }

    private static final class Builder {
        private IdItemPedido idItemPedido;
        private IdEvento idEvento;
        private int quantidade;
        private Monetario valorUnitario;
        private Monetario valorTotal;

        private Builder() {
        }

        public Builder idItemPedido(IdItemPedido val) {
            idItemPedido = val;
            return this;
        }

        public Builder idEvento(IdEvento val) {
            idEvento = val;
            return this;
        }

        public Builder quantidade(int val) {
            quantidade = val;
            return this;
        }

        public Builder valorUnitario(Monetario val) {
            valorUnitario = val;
            return this;
        }

        public Builder valorTotal(Monetario val) {
            valorTotal = val;
            return this;
        }

        public ItemPedido build() {
            return new ItemPedido(this);
        }
    }
}

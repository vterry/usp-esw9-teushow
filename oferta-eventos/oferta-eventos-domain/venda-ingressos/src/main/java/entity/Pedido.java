package entity;

import valueobjects.*;

import java.util.List;
import java.util.UUID;

public class Pedido extends AggregateRoot<IdPedido> {
    private final IdCliente idCliente;
    private final TipoEntrega tipoEntrega;
    private final Endereco localRetirada;
    private final List<ItemPedido> ingressos;
    private final Monetario preco;
    private boolean transferivel;

    public void inicializarPedido() {
        setId(new IdPedido(UUID.randomUUID()));
        inicializarItemPedido();
    }

    private void inicializarItemPedido() {
        long itemId = 1;
        for (ItemPedido ingresso: ingressos) {
            ingresso.inicializarItemPedido(super.getId(), new IdItemPedido(itemId++));
        }
    }

    public void pagar(){

    }

    public void aprovar(){

    }

    public void iniciarCancelamento(){

    }

    public void cancelar() {

    }

    private Pedido (Builder builder){
        this.idCliente = builder.idCliente;
        this.tipoEntrega = builder.tipoEntrega;
        this.localRetirada = builder.localRetirada;
        this.ingressos = builder.ingressos;
        this.preco = builder.preco;
        this.transferivel = builder.transferivel;
    }

    private static final class Builder {
        private IdPedido idPedido;
        private IdCliente idCliente;
        private TipoEntrega tipoEntrega;
        private Endereco localRetirada;
        private List<ItemPedido> ingressos;
        private Monetario preco;
        private boolean transferivel;

        private Builder() {
        }

        public Builder idPedido(IdPedido val) {
            idPedido = val;
            return this;
        }
        public Builder idCliente(IdCliente val) {
            idCliente = val;
            return this;
        }
        public Builder tipoEntrega(TipoEntrega val) {
            tipoEntrega = val;
            return this;
        }
        public Builder localRetirada(Endereco val) {
            localRetirada = val;
            return this;
        }
        public Builder ingressos(List<ItemPedido> val) {
            ingressos = val;
            return this;
        }
        public Builder preco(Monetario val) {
            preco = val;
            return this;
        }
        public Builder transferivel(boolean val) {
            transferivel = val;
            return this;
        }

        public Pedido build() {
            return new Pedido(this);
        }
    }


}

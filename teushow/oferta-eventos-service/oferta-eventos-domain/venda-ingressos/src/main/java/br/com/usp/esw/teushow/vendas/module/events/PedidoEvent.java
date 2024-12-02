package br.com.usp.esw.teushow.vendas.module.events;

import br.com.usp.esw.common.domain.event.DomainEvent;
import br.com.usp.esw.teushow.vendas.module.entity.Pedido;

import java.time.ZonedDateTime;

public abstract class PedidoEvent implements DomainEvent<Pedido> {
    private final Pedido pedido;
    private final ZonedDateTime dataCriacao;

    public PedidoEvent(Pedido pedido, ZonedDateTime dataCriacao){
        this.pedido = pedido;
        this.dataCriacao = dataCriacao;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public ZonedDateTime getDataCriacao() {
        return dataCriacao;
    }
}

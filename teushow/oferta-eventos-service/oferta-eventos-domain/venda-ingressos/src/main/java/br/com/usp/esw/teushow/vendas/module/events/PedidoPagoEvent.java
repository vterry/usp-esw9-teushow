package br.com.usp.esw.teushow.vendas.module.events;

import br.com.usp.esw.common.domain.event.publisher.DomainEventPublisher;
import br.com.usp.esw.teushow.vendas.module.entity.Pedido;

import java.time.ZonedDateTime;

public class PedidoPagoEvent extends PedidoEvent {

    private final DomainEventPublisher<PedidoPagoEvent> pedidoPagoEventDomainEventPublisher;

    public PedidoPagoEvent(Pedido pedido, ZonedDateTime dataCriacao, DomainEventPublisher<PedidoPagoEvent> pedidoPagoEventDomainEventPublisher) {
        super(pedido, dataCriacao);
        this.pedidoPagoEventDomainEventPublisher = pedidoPagoEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        pedidoPagoEventDomainEventPublisher.publish(this);
    }
}

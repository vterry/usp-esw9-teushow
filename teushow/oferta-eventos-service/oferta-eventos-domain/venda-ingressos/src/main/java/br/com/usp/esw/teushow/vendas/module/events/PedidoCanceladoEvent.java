package br.com.usp.esw.teushow.vendas.module.events;

import br.com.usp.esw.common.domain.event.publisher.DomainEventPublisher;
import br.com.usp.esw.teushow.vendas.module.entity.Pedido;

import java.time.ZonedDateTime;

public class PedidoCanceladoEvent extends PedidoEvent {

    private final DomainEventPublisher<PedidoCanceladoEvent> pedidoCanceladoEventDomainEventPublisher;

    public PedidoCanceladoEvent(Pedido pedido, ZonedDateTime dataCriacao, DomainEventPublisher<PedidoCanceladoEvent> pedidoCanceladoEventDomainEventPublisher) {
        super(pedido, dataCriacao);
        this.pedidoCanceladoEventDomainEventPublisher = pedidoCanceladoEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        pedidoCanceladoEventDomainEventPublisher.publish(this);
    }
}

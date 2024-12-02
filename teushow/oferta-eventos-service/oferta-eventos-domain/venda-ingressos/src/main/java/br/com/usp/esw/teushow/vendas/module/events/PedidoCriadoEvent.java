package br.com.usp.esw.teushow.vendas.module.events;

import br.com.usp.esw.common.domain.event.publisher.DomainEventPublisher;
import br.com.usp.esw.teushow.vendas.module.entity.Pedido;

import java.time.ZonedDateTime;

public class PedidoCriadoEvent extends PedidoEvent {

    private final DomainEventPublisher<PedidoCriadoEvent> pedidoCriadoEventDomainEventPublisher;

    public PedidoCriadoEvent(Pedido pedido, ZonedDateTime dataCriacao, DomainEventPublisher<PedidoCriadoEvent> pedidoCriadoEventDomainEventPublisher) {
        super(pedido, dataCriacao);
        this.pedidoCriadoEventDomainEventPublisher = pedidoCriadoEventDomainEventPublisher;
    }

    @Override
    public void fire() {
        pedidoCriadoEventDomainEventPublisher.publish(this);
    }
}
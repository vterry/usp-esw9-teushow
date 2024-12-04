package br.com.usp.esw.teushow.ofertas.service.domain.ports.output.message.publisher.aprovacaoevento;

import br.com.usp.esw.common.domain.event.publisher.DomainEventPublisher;
import br.com.usp.esw.teushow.vendas.module.events.PedidoCanceladoEvent;

public interface CancelarPagamentoPedidoRequestMessagePublisher extends DomainEventPublisher<PedidoCanceladoEvent> {
}

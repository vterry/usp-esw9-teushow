package br.com.usp.esw.teushow.ofertas.service.domain.ports.output.message.publisher.pagamento;

import br.com.usp.esw.common.domain.event.publisher.DomainEventPublisher;
import br.com.usp.esw.teushow.vendas.module.events.PedidoPagoEvent;

public interface PagamentoPedidoRequestMessageHandler extends DomainEventPublisher<PedidoPagoEvent> {
}

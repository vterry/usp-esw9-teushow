package br.com.usp.esw.teushow.ofertas.domain;

import br.com.usp.esw.common.domain.event.publisher.DomainEventPublisher;
import br.com.usp.esw.teushow.vendas.module.entity.Evento;
import br.com.usp.esw.teushow.vendas.module.entity.Pedido;
import br.com.usp.esw.teushow.vendas.module.events.PedidoCanceladoEvent;
import br.com.usp.esw.teushow.vendas.module.events.PedidoCriadoEvent;
import br.com.usp.esw.teushow.vendas.module.events.PedidoPagoEvent;

import java.util.List;

public interface OfertaDeEventosDomainService {
    PedidoCriadoEvent validarEIniciarPedido(Pedido pedido, Evento evento, DomainEventPublisher<PedidoCriadoEvent> pedidoCriadoEventDomainEventPublisher);

    PedidoPagoEvent pagarPedido(Pedido pedido, DomainEventPublisher<PedidoPagoEvent> pedidoPagoEventDomainEventPublisher);

    void aprovarPedido(Pedido pedido);

    PedidoCanceladoEvent iniciarCancelamento(Pedido pedido, List<String> historicoFalhas, DomainEventPublisher<PedidoCanceladoEvent> pedidoCanceladoEventDomainEventPublisher);

    void cancelarPedido(Pedido pedido, List<String> historicoFalhas);

}

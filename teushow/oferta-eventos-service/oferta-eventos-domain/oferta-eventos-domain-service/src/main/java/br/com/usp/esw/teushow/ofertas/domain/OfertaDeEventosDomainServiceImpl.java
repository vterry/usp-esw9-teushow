package br.com.usp.esw.teushow.ofertas.domain;

import br.com.usp.esw.common.domain.event.publisher.DomainEventPublisher;
import br.com.usp.esw.teushow.vendas.module.entity.Evento;
import br.com.usp.esw.teushow.vendas.module.entity.Ingresso;
import br.com.usp.esw.teushow.vendas.module.entity.Pedido;
import br.com.usp.esw.teushow.vendas.module.events.PedidoCanceladoEvent;
import br.com.usp.esw.teushow.vendas.module.events.PedidoCriadoEvent;
import br.com.usp.esw.teushow.vendas.module.events.PedidoPagoEvent;
import br.com.usp.esw.teushow.vendas.module.exception.VendaIngressoModuleException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import static br.com.usp.esw.common.domain.constants.DomainConstants.UTC;

public class OfertaDeEventosDomainServiceImpl implements OfertaDeEventosDomainService{

    @Override
    public PedidoCriadoEvent validarEIniciarPedido(Pedido pedido, Evento evento, DomainEventPublisher<PedidoCriadoEvent> pedidoCriadoEventDomainEventPublisher) {
        validarEvento(evento);
        adicionarInformacaoIngresso(pedido, evento);
        pedido.inicializarPedido();
        pedido.validarPedido();
        System.out.println("Pedido com id:" + pedido.getId().getValue() + "foi iniciado!");
        return new PedidoCriadoEvent(pedido, ZonedDateTime.now(ZoneId.of(UTC)), pedidoCriadoEventDomainEventPublisher);
    }

    @Override
    public PedidoPagoEvent pagarPedido(Pedido pedido, DomainEventPublisher<PedidoPagoEvent> pedidoPagoEventDomainEventPublisher) {
        pedido.pagar();
        System.out.println("Pedido com id:" + pedido.getId().getValue() + "foi pago!");
        return new PedidoPagoEvent(pedido, ZonedDateTime.now(ZoneId.of(UTC)), pedidoPagoEventDomainEventPublisher);
    }

    @Override
    public void aprovarPedido(Pedido pedido) {
        pedido.aprovar();
        System.out.println("Pedido com id:" + pedido.getId().getValue() + "foi aprovado!");
    }

    @Override
    public PedidoCanceladoEvent iniciarCancelamento(Pedido pedido, List<String> historicoFalhas, DomainEventPublisher<PedidoCanceladoEvent> pedidoCanceladoEventDomainEventPublisher) {
        pedido.iniciarCancelamento(historicoFalhas);
        System.out.println("Cancelamento do pedido com id:" + pedido.getId().getValue() + "foi iniciado!");
        return new PedidoCanceladoEvent(pedido, ZonedDateTime.now(ZoneId.of(UTC)), pedidoCanceladoEventDomainEventPublisher);
    }

    @Override
    public void cancelarPedido(Pedido pedido, List<String> historicoFalhas) {
        pedido.cancelar(historicoFalhas);
        System.out.println("Cancelamento do pedido com id:" + pedido.getId().getValue() + "foi efetivado!");
    }

    private void validarEvento(Evento evento){
        if (!evento.isAtivo()){
            throw new VendaIngressoModuleException("Evento com o id: " + evento.getId().getValue() + " não está mais ativo");
        }
    }

    private void adicionarInformacaoIngresso(Pedido pedido, Evento evento){
        pedido.getIngressos().forEach(itemPedido -> evento.getIngressos().forEach(ingresso -> {
            Ingresso ingressoSelecionado = itemPedido.getIngresso();
            if(ingressoSelecionado.equals(ingresso)){
                ingressoSelecionado.atualizarIngresso(ingresso.getPreco(), ingresso.getTipoIngresso());
            }
        }));
    }
}

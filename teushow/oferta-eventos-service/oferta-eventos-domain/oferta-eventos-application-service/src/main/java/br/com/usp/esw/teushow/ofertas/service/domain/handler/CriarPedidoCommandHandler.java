package br.com.usp.esw.teushow.ofertas.service.domain.handler;

import br.com.usp.esw.teushow.ofertas.service.domain.dto.create.CriarPedidoCommand;
import br.com.usp.esw.teushow.ofertas.service.domain.dto.create.CriarPedidoResponse;
import br.com.usp.esw.teushow.ofertas.service.domain.mapper.PedidoDataMapper;
import br.com.usp.esw.teushow.ofertas.service.domain.ports.output.message.publisher.aprovacaoevento.PedidoCriadoPagamentoRequestMessagePublisher;
import br.com.usp.esw.teushow.vendas.module.events.PedidoCriadoEvent;
import org.springframework.stereotype.Component;

@Component
public class CriarPedidoCommandHandler {

    private final CriarPedidoHelper criarPedidoHelper;

    private final PedidoDataMapper dataMapper;

    private final PedidoCriadoPagamentoRequestMessagePublisher pedidoCriadoPagamentoRequestMessagePublisher;

    public CriarPedidoCommandHandler(CriarPedidoHelper criarPedidoHelper, PedidoDataMapper dataMapper, PedidoCriadoPagamentoRequestMessagePublisher pedidoCriadoPagamentoRequestMessagePublisher) {
        this.criarPedidoHelper = criarPedidoHelper;
        this.dataMapper = dataMapper;
        this.pedidoCriadoPagamentoRequestMessagePublisher = pedidoCriadoPagamentoRequestMessagePublisher;
    }

    public CriarPedidoResponse criarPedido(CriarPedidoCommand criarPedidoCommand){
        PedidoCriadoEvent pedidoCriadoEvent = criarPedidoHelper.salvarPedido(criarPedidoCommand);
        System.out.println("O pedido foi criado - ID PEDIDO: " + pedidoCriadoEvent.getPedido().getId().getValue());
        pedidoCriadoPagamentoRequestMessagePublisher.publish(pedidoCriadoEvent);
        return dataMapper.pedidoParaCriarPedidoResponse(pedidoCriadoEvent.getPedido());
    }
}

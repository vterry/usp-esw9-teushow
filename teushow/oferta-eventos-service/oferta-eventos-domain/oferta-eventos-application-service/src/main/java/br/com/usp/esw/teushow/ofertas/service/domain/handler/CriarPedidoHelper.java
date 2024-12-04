package br.com.usp.esw.teushow.ofertas.service.domain.handler;

import br.com.usp.esw.teushow.ofertas.domain.OfertaDeEventosDomainService;
import br.com.usp.esw.teushow.ofertas.service.domain.dto.create.CriarPedidoCommand;
import br.com.usp.esw.teushow.ofertas.service.domain.mapper.PedidoDataMapper;
import br.com.usp.esw.teushow.ofertas.service.domain.ports.output.repository.ClienteRepository;
import br.com.usp.esw.teushow.ofertas.service.domain.ports.output.repository.EventoRepository;
import br.com.usp.esw.teushow.ofertas.service.domain.ports.output.repository.PedidoRepository;
import br.com.usp.esw.teushow.vendas.module.entity.Cliente;
import br.com.usp.esw.teushow.vendas.module.entity.Evento;
import br.com.usp.esw.teushow.vendas.module.entity.Pedido;
import br.com.usp.esw.teushow.vendas.module.events.PedidoCriadoEvent;
import br.com.usp.esw.teushow.vendas.module.exception.VendaIngressoModuleException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
public class CriarPedidoHelper {

    private final OfertaDeEventosDomainService ofertaDeEventosDomainServices;

    private final PedidoRepository pedidoRepository;

    private final ClienteRepository clienteRepository;

    private final EventoRepository eventoRepository;

    private final PedidoDataMapper pedidoDataMapper;

    public CriarPedidoHelper(OfertaDeEventosDomainService ofertaDeEventosDomainServices, PedidoRepository pedidoRepository, ClienteRepository clienteRepository, EventoRepository eventoRepository, PedidoDataMapper pedidoDataMapper) {
        this.ofertaDeEventosDomainServices = ofertaDeEventosDomainServices;
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.eventoRepository = eventoRepository;
        this.pedidoDataMapper = pedidoDataMapper;
    }

    @Transactional
    public PedidoCriadoEvent salvarPedido(CriarPedidoCommand criarPedidoCommand){
        validarCliente(criarPedidoCommand.getIdCliente());
        Evento evento = validarEvento(criarPedidoCommand);
        Pedido pedido = pedidoDataMapper.criarPedidoCommandParaPedido(criarPedidoCommand);
        PedidoCriadoEvent pedidoCriadoEvent = ofertaDeEventosDomainServices.validarEIniciarPedido(pedido, evento, null);
        salvarPedido(pedido);
        System.out.println("Pedido salvo -  ID PEDIDO: " + pedidoCriadoEvent.getPedido().getId().getValue());
        return pedidoCriadoEvent;
    }

    private Evento validarEvento(CriarPedidoCommand criarPedidoCommand){
        Evento evento = pedidoDataMapper.criarPedidoCommandParaEvento(criarPedidoCommand);
        Optional<Evento> optionalEvento = eventoRepository.localiarInforamacaoEvento(evento);

        if(optionalEvento.isEmpty()){
            System.out.println("WARM: Não foi possivel localizar o evento: " + criarPedidoCommand.getIdEvento());
            throw new VendaIngressoModuleException("Não foi possivel localizar o evento: " + criarPedidoCommand.getIdEvento());
        }
        return optionalEvento.get();
    }

    private void validarCliente(UUID idCliente){
        Optional<Cliente> cliente = clienteRepository.localizarCliente(idCliente);
        if(cliente.isEmpty()){
            System.out.println("WARM: Cliente não localizado - ID CLIENTE: " + idCliente);
            throw new VendaIngressoModuleException("Cliente não localizado - ID CLIENTE: " + idCliente);
        }
    }

    private Pedido salvarPedido(Pedido pedido){
        Pedido resultado = pedidoRepository.salvar(pedido);
        if (resultado == null){
            System.out.println("Não foi possivel salver pedido!");
            throw new VendaIngressoModuleException("Não foi possivel salver o pedido");
        }
        System.out.println("Pedido salvo -  ID PEDIDO: " + resultado.getId().getValue());
        return resultado;
    }
}

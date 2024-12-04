package br.com.usp.esw.teushow.ofertas.service.domain.mapper;

import br.com.usp.esw.common.domain.valueobjects.IdCliente;
import br.com.usp.esw.common.domain.valueobjects.IdEvento;
import br.com.usp.esw.common.domain.valueobjects.IdIngresso;
import br.com.usp.esw.common.domain.valueobjects.Money;
import br.com.usp.esw.teushow.ofertas.service.domain.dto.create.CriarPedidoCommand;
import br.com.usp.esw.teushow.ofertas.service.domain.dto.create.CriarPedidoResponse;
import br.com.usp.esw.teushow.ofertas.service.domain.dto.create.EnderecoRetirada;
import br.com.usp.esw.teushow.vendas.module.entity.Evento;
import br.com.usp.esw.teushow.vendas.module.entity.Ingresso;
import br.com.usp.esw.teushow.vendas.module.entity.ItemPedido;
import br.com.usp.esw.teushow.vendas.module.entity.Pedido;
import br.com.usp.esw.teushow.vendas.module.valueobject.Endereco;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class PedidoDataMapper {

    public Evento criarPedidoCommandParaEvento(CriarPedidoCommand criarPedidoCommand){
        return Evento.builder()
                .idEvento(new IdEvento(criarPedidoCommand.getIdEvento()))
                .ingressos(criarPedidoCommand.getIngressos().stream().map(itemPedido ->
                        new Ingresso(new IdIngresso(itemPedido.getIngressoId()))).collect(Collectors.toList()))
                .build();
    }

    public Pedido criarPedidoCommandParaPedido(CriarPedidoCommand criarPedidoCommand){
        return Pedido.builder()
                .idCliente(new IdCliente(criarPedidoCommand.getIdCliente()))
                .idEvento(new IdEvento(criarPedidoCommand.getIdEvento()))
                .tipoEntrega(criarPedidoCommand.getTipoEntrega())
                .localRetirada(enderecoRetiroParaEndereco(criarPedidoCommand.getEnderecoRetirada()))
                .preco(new Money(criarPedidoCommand.getPreco()))
                .ingressos(itensPedidoParaItemPedidoEntity(criarPedidoCommand.getIngressos()))
                .build();
    }


    public CriarPedidoResponse pedidoParaCriarPedidoResponse(Pedido pedido){
        return CriarPedidoResponse.builder()
                .pedidotrackingId(pedido.getTrackingId().getValue())
                .statusDoPedido(pedido.getStatusDoPedido())
                .build();
    }

    private List<ItemPedido> itensPedidoParaItemPedidoEntity(List<br.com.usp.esw.teushow.ofertas.service.domain.dto.create.ItemPedido> itemPedidos){
        return itemPedidos.stream().map(itemPedido ->
                ItemPedido.builder()
                        .ingresso(new Ingresso(new IdIngresso(itemPedido.getIngressoId())))
                        .preco(new Money(itemPedido.getPreco()))
                        .quantidade(itemPedido.getQuantidade())
                        .subTotal(new Money(itemPedido.getValorTotal()))
                        .build()).collect(Collectors.toList());
    }


    private Endereco enderecoRetiroParaEndereco(EnderecoRetirada enderecoRetirada){
        return new Endereco(
                UUID.randomUUID(),
                enderecoRetirada.getRua(),
                enderecoRetirada.getCodigoPostal(),
                enderecoRetirada.getCidade()
        );
    }

}

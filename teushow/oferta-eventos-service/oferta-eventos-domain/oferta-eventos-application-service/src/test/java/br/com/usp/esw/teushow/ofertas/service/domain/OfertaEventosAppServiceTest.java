package br.com.usp.esw.teushow.ofertas.service.domain;

import br.com.usp.esw.common.domain.valueobjects.*;
import br.com.usp.esw.teushow.ofertas.service.domain.dto.create.CriarPedidoCommand;
import br.com.usp.esw.teushow.ofertas.service.domain.dto.create.CriarPedidoResponse;
import br.com.usp.esw.teushow.ofertas.service.domain.dto.create.ItemPedido;
import br.com.usp.esw.teushow.ofertas.service.domain.mapper.PedidoDataMapper;
import br.com.usp.esw.teushow.ofertas.service.domain.ports.input.service.OfertaDeEventosAppService;
import br.com.usp.esw.teushow.ofertas.service.domain.ports.output.repository.ClienteRepository;
import br.com.usp.esw.teushow.ofertas.service.domain.ports.output.repository.EventoRepository;
import br.com.usp.esw.teushow.ofertas.service.domain.ports.output.repository.PedidoRepository;
import br.com.usp.esw.teushow.vendas.module.entity.Cliente;
import br.com.usp.esw.teushow.vendas.module.entity.Evento;
import br.com.usp.esw.teushow.vendas.module.entity.Ingresso;
import br.com.usp.esw.teushow.vendas.module.entity.Pedido;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(classes = OfertaEventosAppServiceTest.class)
public class OfertaEventosAppServiceTest {

    @Autowired
    private OfertaDeEventosAppService ofertaDeEventosAppService;

    @Autowired
    private PedidoDataMapper pedidoDataMapper;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private EventoRepository eventoRepository;

    private CriarPedidoCommand criarPedidoCommand;
    private final UUID ID_CLIENTE = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb41");
    private final UUID ID_EVENTO = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb45");
    private final UUID ID_INGRESSO = UUID.fromString("d215b5f8-0249-4dc5-89a3-51fd148cfb48");
    private final UUID ID_INGRESSO_2 = UUID.fromString("0e44d81f-83c5-4d36-9aa1-c7d8271de24a");
    private final UUID ID_PEDIDO = UUID.fromString("15a497c1-0f4b-4eff-b9f4-c402c8c07afb");
    private final BigDecimal PRECO = new BigDecimal("200.00");

    @BeforeAll
    public  void init(){
        criarPedidoCommand = CriarPedidoCommand.builder()
                .idCliente(ID_CLIENTE)
                .idEvento(ID_EVENTO)
                .tipoEntrega(TipoEntrega.VIA_DIGITAL)
                .enderecoRetirada(null)
                .preco(PRECO)
                .ingressos(List.of(ItemPedido.builder()
                        .ingressoId(ID_INGRESSO)
                        .quantidade(1)
                        .preco(new BigDecimal("50.00"))
                        .valorTotal(new BigDecimal("50.00"))
                        .build(),
                        ItemPedido.builder()
                                .ingressoId(ID_INGRESSO)
                                .quantidade(3)
                                .preco(new BigDecimal("50"))
                                .valorTotal(new BigDecimal("150.00"))
                                .build()))
                .build();

        Cliente cliente = new Cliente();
        cliente.setId(new IdCliente(ID_CLIENTE));

        Evento eventoResponse = Evento.builder()
                .idEvento(new IdEvento(criarPedidoCommand.getIdEvento()))
                .ingressos(List.of(new Ingresso(new IdIngresso(ID_INGRESSO), new Money(new BigDecimal("50.00")), TipoIngresso.COMUN),new Ingresso(new IdIngresso(ID_INGRESSO_2), new Money(new BigDecimal("10.00")), TipoIngresso.VIP)))
                .ativo(true)
                .build();

        Pedido pedido = pedidoDataMapper.criarPedidoCommandParaPedido(criarPedidoCommand);
        pedido.setId(new IdPedido(ID_PEDIDO));

        when(clienteRepository.localizarCliente(ID_CLIENTE)).thenReturn(Optional.of(cliente));
        when(eventoRepository.localiarInforamacaoEvento(pedidoDataMapper.criarPedidoCommandParaEvento(criarPedidoCommand))).thenReturn(Optional.of(eventoResponse));
        when(pedidoRepository.salvar(any(Pedido.class))).thenReturn(pedido);
    }

    @Test
    public void testarCriarPedido(){
        CriarPedidoResponse criarPedidoResponse = ofertaDeEventosAppService.createOrder(criarPedidoCommand);
        assertEquals(criarPedidoResponse.getStatusDoPedido(), StatusDoPedido.RESERVADO);
        assertEquals(criarPedidoResponse.getMensagem(), "");
        assertNotNull(criarPedidoResponse.getPedidotrackingId());
    }

}

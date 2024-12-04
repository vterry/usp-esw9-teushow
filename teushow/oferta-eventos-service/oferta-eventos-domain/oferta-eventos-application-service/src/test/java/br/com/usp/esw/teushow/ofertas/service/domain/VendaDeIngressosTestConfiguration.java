package br.com.usp.esw.teushow.ofertas.service.domain;

import br.com.usp.esw.teushow.ofertas.domain.OfertaDeEventosDomainService;
import br.com.usp.esw.teushow.ofertas.domain.OfertaDeEventosDomainServiceImpl;
import br.com.usp.esw.teushow.ofertas.service.domain.ports.output.message.publisher.aprovacaoevento.CancelarPagamentoPedidoRequestMessagePublisher;
import br.com.usp.esw.teushow.ofertas.service.domain.ports.output.message.publisher.aprovacaoevento.PedidoCriadoPagamentoRequestMessagePublisher;
import br.com.usp.esw.teushow.ofertas.service.domain.ports.output.message.publisher.pagamento.PagamentoPedidoRequestMessageHandler;
import br.com.usp.esw.teushow.ofertas.service.domain.ports.output.repository.ClienteRepository;
import br.com.usp.esw.teushow.ofertas.service.domain.ports.output.repository.EventoRepository;
import br.com.usp.esw.teushow.ofertas.service.domain.ports.output.repository.PedidoRepository;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication(scanBasePackages = "br.com.usp.esw.teushow")
@ComponentScan(basePackages = "br.com.usp.esw.teushow")
public class VendaDeIngressosTestConfiguration {

    @Bean
    public PedidoCriadoPagamentoRequestMessagePublisher pedidoCriadoPagamentoRequestMessagePublisher(){
        return Mockito.mock(PedidoCriadoPagamentoRequestMessagePublisher.class);
    }

    @Bean
    public CancelarPagamentoPedidoRequestMessagePublisher cancelarPagamentoPedidoRequestMessagePublisher(){
        return Mockito.mock(CancelarPagamentoPedidoRequestMessagePublisher.class);
    }

    @Bean
    public PagamentoPedidoRequestMessageHandler pagamentoPedidoRequestMessageHandler(){
        return Mockito.mock(PagamentoPedidoRequestMessageHandler.class);
    }

    @Bean
    public PedidoRepository pedidoRepository(){
        return Mockito.mock(PedidoRepository.class);
    }

    @Bean
    public ClienteRepository clienteRepository(){
        return Mockito.mock(ClienteRepository.class);
    }

    @Bean
    public EventoRepository eventoRepository(){
        return Mockito.mock(EventoRepository.class);
    }

    @Bean
    public OfertaDeEventosDomainService ofertaDeEventosDomainService(){
        return new OfertaDeEventosDomainServiceImpl();
    }
}

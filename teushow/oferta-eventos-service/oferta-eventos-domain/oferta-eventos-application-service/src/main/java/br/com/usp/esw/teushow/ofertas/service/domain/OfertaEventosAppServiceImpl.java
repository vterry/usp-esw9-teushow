package br.com.usp.esw.teushow.ofertas.service.domain;

import br.com.usp.esw.teushow.ofertas.service.domain.dto.create.CriarPedidoCommand;
import br.com.usp.esw.teushow.ofertas.service.domain.dto.create.CriarPedidoResponse;
import br.com.usp.esw.teushow.ofertas.service.domain.dto.track.RastreioPedidoQuery;
import br.com.usp.esw.teushow.ofertas.service.domain.dto.track.RastreioPedidoResponse;
import br.com.usp.esw.teushow.ofertas.service.domain.handler.CriarPedidoCommandHandler;
import br.com.usp.esw.teushow.ofertas.service.domain.handler.RastreioPedidoCommandHandler;
import br.com.usp.esw.teushow.ofertas.service.domain.ports.input.service.OfertaDeEventosAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class OfertaEventosAppServiceImpl implements OfertaDeEventosAppService {

    private final CriarPedidoCommandHandler criarPedidoCommandHandler;

    private final RastreioPedidoCommandHandler rastreioPedidoCommandHandler;

    public OfertaEventosAppServiceImpl(CriarPedidoCommandHandler criarPedidoCommandHandler, RastreioPedidoCommandHandler rastreioPedidoCommandHandler) {
        this.criarPedidoCommandHandler = criarPedidoCommandHandler;
        this.rastreioPedidoCommandHandler = rastreioPedidoCommandHandler;
    }

    @Override
    public CriarPedidoResponse createOrder(CriarPedidoCommand criarPedidoCommand) {
        return criarPedidoCommandHandler.criarPedido(criarPedidoCommand);
    }

    @Override
    public RastreioPedidoResponse trackOrder(RastreioPedidoQuery rastreioPedidoQuery) {
        return rastreioPedidoCommandHandler.rastrearPedido(rastreioPedidoQuery);
    }

}

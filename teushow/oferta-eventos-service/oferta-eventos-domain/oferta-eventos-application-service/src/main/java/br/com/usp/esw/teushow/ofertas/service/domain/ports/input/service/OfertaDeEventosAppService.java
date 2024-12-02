package br.com.usp.esw.teushow.ofertas.service.domain.ports.input.service;

import br.com.usp.esw.teushow.ofertas.service.domain.dto.create.CriarPedidoCommand;
import br.com.usp.esw.teushow.ofertas.service.domain.dto.create.CriarPedidoResponse;
import br.com.usp.esw.teushow.ofertas.service.domain.dto.track.RastreioPedidoQuery;
import br.com.usp.esw.teushow.ofertas.service.domain.dto.track.RastreioPedidoResponse;
import jakarta.validation.Valid;

public interface OfertaDeEventosAppService {

    CriarPedidoResponse createOrder(@Valid CriarPedidoCommand criarPedidoCommand);

    RastreioPedidoResponse trackOrder(@Valid RastreioPedidoQuery rastreioPedidoQuery);

}

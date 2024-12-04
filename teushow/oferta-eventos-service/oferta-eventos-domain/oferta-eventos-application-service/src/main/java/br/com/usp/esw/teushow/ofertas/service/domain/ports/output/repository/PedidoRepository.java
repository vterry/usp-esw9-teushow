package br.com.usp.esw.teushow.ofertas.service.domain.ports.output.repository;

import br.com.usp.esw.teushow.vendas.module.entity.Pedido;
import br.com.usp.esw.teushow.vendas.module.valueobject.TrackingId;

import java.util.Optional;

public interface PedidoRepository {

    Pedido salvar(Pedido pedido);

    Optional<Pedido> localizarPorTrackingId(TrackingId trackingId);

}

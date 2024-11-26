package repository;

import entity.PedidoDeIngresso;
import valueobjects.IdCliente;
import valueobjects.IdEvento;
import valueobjects.IdPedido;

import java.util.List;
import java.util.Optional;

public interface PedidoIngressoReposity {

    PedidoDeIngresso save(PedidoDeIngresso pedidoDeIngresso);

    Optional<PedidoDeIngresso> buscarPedidoPorId(IdPedido idPedido);

    List<PedidoDeIngresso> buscarPedidosPorCliente(IdCliente idCliente);

    List<PedidoDeIngresso> buscarPedidosPorEvento(IdEvento idEvento);
}

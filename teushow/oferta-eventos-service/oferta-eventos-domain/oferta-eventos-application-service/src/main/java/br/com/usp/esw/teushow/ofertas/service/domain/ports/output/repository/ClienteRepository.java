package br.com.usp.esw.teushow.ofertas.service.domain.ports.output.repository;

import br.com.usp.esw.teushow.vendas.module.entity.Cliente;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository {

    Optional<Cliente> localizarCliente(UUID idCliente);
}

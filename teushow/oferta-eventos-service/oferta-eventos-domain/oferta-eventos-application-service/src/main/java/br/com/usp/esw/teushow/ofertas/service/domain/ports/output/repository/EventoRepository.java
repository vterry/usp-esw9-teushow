package br.com.usp.esw.teushow.ofertas.service.domain.ports.output.repository;

import br.com.usp.esw.teushow.vendas.module.entity.Evento;

import java.util.Optional;

public interface EventoRepository {

    Optional<Evento> localiarInforamacaoEvento(Evento evento);
}

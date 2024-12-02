package br.com.usp.esw.teushow.vendas.module.entity;

import br.com.usp.esw.common.domain.entity.AggregateRoot;
import br.com.usp.esw.common.domain.valueobjects.IdCliente;

public class Cliente extends AggregateRoot<IdCliente> {
    public Cliente() {

    }

    public Cliente(IdCliente idCliente) {
        super.setId(idCliente);
    }
}

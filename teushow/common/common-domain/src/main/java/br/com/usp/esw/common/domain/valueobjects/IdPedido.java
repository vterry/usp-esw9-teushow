package br.com.usp.esw.common.domain.valueobjects;

import java.util.UUID;

public class IdPedido extends BaseId<UUID> {
    public IdPedido(UUID value) {
        super(value);
    }
}

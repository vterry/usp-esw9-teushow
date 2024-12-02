package br.com.usp.esw.teushow.vendas.module.valueobject;

import br.com.usp.esw.common.domain.valueobjects.BaseId;

import java.util.UUID;

public class TrackingId extends BaseId<UUID> {
    public TrackingId(UUID value) {
        super(value);
    }
}

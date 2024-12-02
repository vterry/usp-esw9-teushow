package br.com.usp.esw.common.domain.valueobjects;

import java.util.UUID;

public class IdEvento extends BaseId<UUID>{
    public IdEvento(UUID value) {
        super(value);
    }
}

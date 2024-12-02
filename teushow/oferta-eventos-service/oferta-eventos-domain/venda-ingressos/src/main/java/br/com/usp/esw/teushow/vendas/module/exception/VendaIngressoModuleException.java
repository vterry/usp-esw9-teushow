package br.com.usp.esw.teushow.vendas.module.exception;

import br.com.usp.esw.common.domain.exception.DomainException;

public class VendaIngressoModuleException extends DomainException {

    public VendaIngressoModuleException(String message) {
        super(message);
    }

    public VendaIngressoModuleException(String message, Throwable cause) {
        super(message, cause);
    }
}

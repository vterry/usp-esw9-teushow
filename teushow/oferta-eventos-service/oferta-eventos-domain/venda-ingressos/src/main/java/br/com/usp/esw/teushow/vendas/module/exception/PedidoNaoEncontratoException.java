package br.com.usp.esw.teushow.vendas.module.exception;

import br.com.usp.esw.common.domain.exception.DomainException;

public class PedidoNaoEncontratoException extends DomainException {
    public PedidoNaoEncontratoException(String message) {
        super(message);
    }

    public PedidoNaoEncontratoException(String message, Throwable cause) {
        super(message, cause);
    }
}

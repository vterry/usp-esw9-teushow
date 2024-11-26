package exception;

public class PedidoDomainException extends DomainException {

    public PedidoDomainException(String message) {
        super(message);
    }

    public PedidoDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}

package br.com.usp.esw.teushow.ofertas.service.domain.dto.track;

import br.com.usp.esw.common.domain.valueobjects.StatusDoPedido;

import java.util.List;
import java.util.UUID;

public class RastreioPedidoResponse {

    private final UUID pedidoTrackingIdl;

    private StatusDoPedido statusDoPedido;

    private List<String> historicoFalhas;

    private RastreioPedidoResponse(Builder builder) {
        pedidoTrackingIdl = builder.pedidoTrackingIdl;
        statusDoPedido = builder.statusDoPedido;
        historicoFalhas = builder.historicoFalhas;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getPedidoTrackingIdl() {
        return pedidoTrackingIdl;
    }

    public StatusDoPedido getStatusDoPedido() {
        return statusDoPedido;
    }

    public List<String> getHistoricoFalhas() {
        return historicoFalhas;
    }

    public static final class Builder {
        private UUID pedidoTrackingIdl;
        private StatusDoPedido statusDoPedido;
        private List<String> historicoFalhas;

        private Builder() {
        }

        public Builder pedidoTrackingIdl(UUID val) {
            pedidoTrackingIdl = val;
            return this;
        }

        public Builder statusDoPedido(StatusDoPedido val) {
            statusDoPedido = val;
            return this;
        }

        public Builder historicoFalhas(List<String> val) {
            historicoFalhas = val;
            return this;
        }

        public RastreioPedidoResponse build() {
            return new RastreioPedidoResponse(this);
        }
    }
}

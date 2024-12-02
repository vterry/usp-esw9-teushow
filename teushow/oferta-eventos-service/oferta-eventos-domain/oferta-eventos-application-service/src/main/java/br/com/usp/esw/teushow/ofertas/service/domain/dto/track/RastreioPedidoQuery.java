package br.com.usp.esw.teushow.ofertas.service.domain.dto.track;

import java.util.UUID;

public class RastreioPedidoQuery {

    private final UUID pedidoTrackingId;

    private RastreioPedidoQuery(Builder builder) {
        pedidoTrackingId = builder.pedidoTrackingId;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getPedidoTrackingId() {
        return pedidoTrackingId;
    }

    public static final class Builder {
        private UUID pedidoTrackingId;

        private Builder() {
        }

        public Builder pedidoTrackingId(UUID val) {
            pedidoTrackingId = val;
            return this;
        }

        public RastreioPedidoQuery build() {
            return new RastreioPedidoQuery(this);
        }
    }
}

package br.com.usp.esw.teushow.ofertas.service.domain.dto.create;

import br.com.usp.esw.common.domain.valueobjects.StatusDoPedido;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class CriarPedidoResponse {

    @NotNull
    private final UUID pedidotrackingId;
    @NotNull
    private final StatusDoPedido statusDoPedido;
    @NotNull
    private final String mensagem;

    private CriarPedidoResponse(Builder builder) {
        pedidotrackingId = builder.pedidotrackingId;
        statusDoPedido = builder.statusDoPedido;
        mensagem = builder.mensagem;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getPedidotrackingId() {
        return pedidotrackingId;
    }

    public StatusDoPedido getStatusDoPedido() {
        return statusDoPedido;
    }

    public String getMensagem() {
        return mensagem;
    }

    public static final class Builder {
        private UUID pedidotrackingId;
        private StatusDoPedido statusDoPedido;
        private String mensagem;

        private Builder() {
        }

        public Builder pedidotrackingId(UUID val) {
            pedidotrackingId = val;
            return this;
        }

        public Builder statusDoPedido(StatusDoPedido val) {
            statusDoPedido = val;
            return this;
        }

        public Builder mensagem(String val) {
            mensagem = val;
            return this;
        }

        public CriarPedidoResponse build() {
            return new CriarPedidoResponse(this);
        }
    }
}

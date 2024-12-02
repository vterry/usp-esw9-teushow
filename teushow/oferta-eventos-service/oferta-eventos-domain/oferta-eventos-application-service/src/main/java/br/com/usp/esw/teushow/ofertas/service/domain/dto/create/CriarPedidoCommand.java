package br.com.usp.esw.teushow.ofertas.service.domain.dto.create;

import br.com.usp.esw.common.domain.valueobjects.TipoEntrega;
import br.com.usp.esw.common.domain.valueobjects.TipoIngresso;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class CriarPedidoCommand {

    @NotNull
    private final UUID idCliente;
    @NotNull
    private final UUID idEvento;
    @NotNull
    private final BigDecimal preco;
    @NotNull
    private final List<ItemPedido> ingressos;

    private final TipoEntrega tipoEntrega;

    private final EnderecoRetirada enderecoRetirada;

    private CriarPedidoCommand(Builder builder) {
        idCliente = builder.idCliente;
        idEvento = builder.idEvento;
        preco = builder.preco;
        ingressos = builder.ingressos;
        enderecoRetirada = builder.enderecoRetirada;
        tipoEntrega = builder.tipoEntrega;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public UUID getIdEvento() {
        return idEvento;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public List<ItemPedido> getIngressos() {
        return ingressos;
    }

    public TipoEntrega getTipoEntrega() {
        return tipoEntrega;
    }

    public EnderecoRetirada getEnderecoRetirada() {
        return enderecoRetirada;
    }

    public static final class Builder {
        private UUID idCliente;
        private UUID idEvento;
        private BigDecimal preco;
        private List<ItemPedido> ingressos;
        private TipoEntrega tipoEntrega;
        private EnderecoRetirada enderecoRetirada;

        private Builder() {
        }

        public Builder idCliente(UUID val) {
            idCliente = val;
            return this;
        }

        public Builder idEvento(UUID val) {
            idEvento = val;
            return this;
        }

        public Builder preco(BigDecimal val) {
            preco = val;
            return this;
        }

        public Builder ingressos(List<ItemPedido> val) {
            ingressos = val;
            return this;
        }

        public Builder tipoEntrega(TipoEntrega val) {
            tipoEntrega = val;
            return this;
        }

        public Builder enderecoRetirada(EnderecoRetirada val) {
            enderecoRetirada = val;
            return this;
        }

        public CriarPedidoCommand build() {
            return new CriarPedidoCommand(this);
        }
    }
}

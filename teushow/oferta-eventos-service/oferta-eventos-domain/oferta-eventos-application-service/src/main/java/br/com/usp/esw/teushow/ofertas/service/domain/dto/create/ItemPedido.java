package br.com.usp.esw.teushow.ofertas.service.domain.dto.create;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public class ItemPedido {

    @NotNull
    private final UUID ingressoId;
    @NotNull
    private final Integer quantidade;
    @NotNull
    private final BigDecimal preco;
    @NotNull
    private final BigDecimal valorTotal;

    private ItemPedido(Builder builder) {
        ingressoId = builder.ingressoId;
        quantidade = builder.quantidade;
        preco = builder.preco;
        valorTotal = builder.valorTotal;
    }

    public static Builder builder() {
        return new Builder();
    }

    public UUID getIngressoId() {
        return ingressoId;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public static final class Builder {
        private UUID ingressoId;
        private Integer quantidade;
        private BigDecimal preco;
        private BigDecimal valorTotal;

        private Builder() {
        }

        public Builder ingressoId(UUID val) {
            ingressoId = val;
            return this;
        }

        public Builder quantidade(Integer val) {
            quantidade = val;
            return this;
        }

        public Builder preco(BigDecimal val) {
            preco = val;
            return this;
        }

        public Builder valorTotal(BigDecimal val) {
            valorTotal = val;
            return this;
        }

        public ItemPedido build() {
            return new ItemPedido(this);
        }
    }
}

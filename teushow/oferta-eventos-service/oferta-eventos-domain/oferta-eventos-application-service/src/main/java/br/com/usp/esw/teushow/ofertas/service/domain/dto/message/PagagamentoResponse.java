package br.com.usp.esw.teushow.ofertas.service.domain.dto.message;

import br.com.usp.esw.common.domain.valueobjects.StatusPagamento;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public class PagagamentoResponse {

    private String id;
    private String sagaId;
    private String idPedido;
    private String idPagamento;
    private String idCliente;
    private BigDecimal preco;
    private Instant dataCriacao;
    private StatusPagamento statusPagamento;
    private List<String> historicoFalhas;

    private PagagamentoResponse(Builder builder) {
        id = builder.id;
        sagaId = builder.sagaId;
        idPedido = builder.idPedido;
        idPagamento = builder.idPagamento;
        idCliente = builder.idCliente;
        preco = builder.preco;
        dataCriacao = builder.dataCriacao;
        statusPagamento = builder.statusPagamento;
        historicoFalhas = builder.historicoFalhas;
    }

    public static Builder builder() {
        return new Builder();
    }


    public String getId() {
        return id;
    }

    public String getSagaId() {
        return sagaId;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public String getIdPagamento() {
        return idPagamento;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public StatusPagamento getStatusPagamento() {
        return statusPagamento;
    }

    public List<String> getHistoricoFalhas() {
        return historicoFalhas;
    }

    public static final class Builder {
        private String id;
        private String sagaId;
        private String idPedido;
        private String idPagamento;
        private String idCliente;
        private BigDecimal preco;
        private Instant dataCriacao;
        private StatusPagamento statusPagamento;
        private List<String> historicoFalhas;

        private Builder() {
        }

        public Builder id(String val) {
            id = val;
            return this;
        }

        public Builder sagaId(String val) {
            sagaId = val;
            return this;
        }

        public Builder idPedido(String val) {
            idPedido = val;
            return this;
        }

        public Builder idPagamento(String val) {
            idPagamento = val;
            return this;
        }

        public Builder idCliente(String val) {
            idCliente = val;
            return this;
        }

        public Builder preco(BigDecimal val) {
            preco = val;
            return this;
        }

        public Builder dataCriacao(Instant val) {
            dataCriacao = val;
            return this;
        }

        public Builder statusPagamento(StatusPagamento val) {
            statusPagamento = val;
            return this;
        }

        public Builder historicoFalhas(List<String> val) {
            historicoFalhas = val;
            return this;
        }

        public PagagamentoResponse build() {
            return new PagagamentoResponse(this);
        }
    }
}

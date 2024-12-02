package br.com.usp.esw.teushow.ofertas.service.domain.dto.message;

import br.com.usp.esw.common.domain.valueobjects.AprovacaoPedidoStatus;

import java.time.Instant;
import java.util.List;

public class AprovacaoEventoResponse{

    private String id;
    private String sagaId;
    private String idPedido;
    private String idEvento;
    private Instant dataCriacao;
    private AprovacaoPedidoStatus aprovacaoPedidoStatus;
    private List<String> historicoFalhas;

    private AprovacaoEventoResponse(Builder builder) {
        id = builder.id;
        sagaId = builder.sagaId;
        idPedido = builder.idPedido;
        idEvento = builder.idEvento;
        dataCriacao = builder.dataCriacao;
        aprovacaoPedidoStatus = builder.aprovacaoPedidoStatus;
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

    public String getIdEvento() {
        return idEvento;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public AprovacaoPedidoStatus getAprovacaoPedidoStatus() {
        return aprovacaoPedidoStatus;
    }

    public List<String> getHistoricoFalhas() {
        return historicoFalhas;
    }

    public static final class Builder {
        private String id;
        private String sagaId;
        private String idPedido;
        private String idEvento;
        private Instant dataCriacao;
        private AprovacaoPedidoStatus aprovacaoPedidoStatus;
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

        public Builder idEvento(String val) {
            idEvento = val;
            return this;
        }

        public Builder dataCriacao(Instant val) {
            dataCriacao = val;
            return this;
        }

        public Builder aprovacaoPedidoStatus(AprovacaoPedidoStatus val) {
            aprovacaoPedidoStatus = val;
            return this;
        }

        public Builder historicoFalhas(List<String> val) {
            historicoFalhas = val;
            return this;
        }

        public AprovacaoEventoResponse build() {
            return new AprovacaoEventoResponse(this);
        }
    }
}

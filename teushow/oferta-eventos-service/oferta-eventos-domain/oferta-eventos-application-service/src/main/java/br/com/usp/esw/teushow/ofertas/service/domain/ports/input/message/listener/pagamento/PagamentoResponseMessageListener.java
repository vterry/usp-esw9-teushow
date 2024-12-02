package br.com.usp.esw.teushow.ofertas.service.domain.ports.input.message.listener.pagamento;

import br.com.usp.esw.teushow.ofertas.service.domain.dto.message.PagagamentoResponse;

public interface PagamentoResponseMessageListener {

    void pagamentoConcluido(PagagamentoResponse pagagamentoResponse);

    void pagamentoCancelado(PagagamentoResponse pagagamentoResponse);

}

package br.com.usp.esw.teushow.ofertas.service.domain.ports.input.message.listener.aprovacaoevento;

import br.com.usp.esw.teushow.ofertas.service.domain.dto.message.AprovacaoEventoResponse;

public interface AprovacaoEventoResponseMessageListener {

    void pedidoAprovado(AprovacaoEventoResponse aprovacaoEventoResponse);

    void pedidoRejeitado(AprovacaoEventoResponse aprovacaoEventoResponse);

}

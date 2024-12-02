package br.com.usp.esw.common.domain.event.publisher;

import br.com.usp.esw.common.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {

    void publish(T domainEvent);
}

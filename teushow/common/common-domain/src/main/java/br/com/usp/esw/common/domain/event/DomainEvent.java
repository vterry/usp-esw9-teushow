package br.com.usp.esw.common.domain.event;

public interface DomainEvent<T> {
    void fire();
}

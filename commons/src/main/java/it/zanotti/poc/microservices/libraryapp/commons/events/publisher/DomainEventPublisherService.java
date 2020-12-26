package it.zanotti.poc.microservices.libraryapp.commons.events.publisher;

import it.zanotti.poc.microservices.libraryapp.commons.events.common.DomainEvent;

import java.util.List;

/**
 * @author Michele Zanotti on 26/12/20
 **/
public interface DomainEventPublisherService {
    void publish(String aggregateType, Object aggregateId, List<DomainEvent> domainEvents);
}

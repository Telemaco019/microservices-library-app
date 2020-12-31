package it.zanotti.poc.libraryapp.commons.events.publisher;

import it.zanotti.poc.libraryapp.commons.events.common.DomainEvent;

import java.util.List;

/**
 * @author Michele Zanotti on 26/12/20
 **/
public interface DomainEventPublisherService {
    void publish(String topicName, Object aggregateId, List<DomainEvent> domainEvents);
}

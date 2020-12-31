package it.zanotti.poc.libraryapp.commons.events.publisher;

import it.zanotti.poc.libraryapp.commons.events.common.DomainEvent;

import java.util.List;
import java.util.function.Function;

/**
 * @author Michele Zanotti on 26/12/20
 **/
public abstract class DomainEventPublisher<A, E extends DomainEvent> {
    private final DomainEventPublisherService publisherService;
    private final String topicName;
    private final Function<A, Object> idSupplier;

    protected DomainEventPublisher(DomainEventPublisherService publisherService,
                                   String topicName,
                                   Function<A, Object> idSupplier) {
        this.publisherService = publisherService;
        this.topicName = topicName;
        this.idSupplier = idSupplier;
    }

    public void publish(A aggregate, List<E> events) {
        publisherService.publish(
                topicName,
                idSupplier.apply(aggregate),
                (List<DomainEvent>) events
        );
    }
}

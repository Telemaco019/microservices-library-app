package it.zanotti.poc.microservices.libraryapp.commons.events.publisher;

import it.zanotti.poc.microservices.libraryapp.commons.events.common.DomainEvent;

import java.util.List;
import java.util.function.Function;

/**
 * @author Michele Zanotti on 26/12/20
 **/
public abstract class DomainEventPublisher<A, E extends DomainEvent> {
    private final DomainEventPublisherService publisherService;
    private final Class<A> aggregateType;
    private final Function<A, Object> idSupplier;

    protected DomainEventPublisher(DomainEventPublisherService publisherService,
                                   Class<A> aggregateType,
                                   Function<A, Object> idSupplier) {
        this.publisherService = publisherService;
        this.aggregateType = aggregateType;
        this.idSupplier = idSupplier;
    }

    public void publish(A aggregate, List<E> events) {
        publisherService.publish(
                aggregateType.getName(),
                idSupplier.apply(aggregate),
                (List<DomainEvent>) events
        );
    }
}

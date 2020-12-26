package it.zanotti.poc.microservices.libraryapp.commons.events;

import java.util.List;

/**
 * @author Michele Zanotti on 26/12/20
 **/
public class ResultWithDomainEvents<R, E extends DomainEvent> {
   private final R result;
   private final List<E> events;

    public ResultWithDomainEvents(R result, List<E> events) {
        this.result = result;
        this.events = events;
    }

    public R getResult() {
        return result;
    }

    public List<E> getEvents() {
        return events;
    }
}

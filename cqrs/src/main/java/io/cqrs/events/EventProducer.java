package io.cqrs.events;

public interface EventProducer {
    void produce(String topic, BaseEvent event);
}

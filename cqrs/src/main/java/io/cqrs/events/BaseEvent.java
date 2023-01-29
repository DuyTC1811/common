package io.cqrs.events;

import io.cqrs.messages.Message;

public abstract class BaseEvent extends Message {
    private int version;

    public BaseEvent() {

    }

    public BaseEvent(int version) {
        this.version = version;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}

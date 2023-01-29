package io.cqrs.commands;

import io.cqrs.messages.Message;

public abstract class BaseCommand extends Message {
    public BaseCommand() {
    }

    public BaseCommand(String id) {
        super(id);
    }

}

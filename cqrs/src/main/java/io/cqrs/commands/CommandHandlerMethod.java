package io.cqrs.commands;

public interface CommandHandlerMethod <T extends BaseCommand>{
    void handle(T command);
}

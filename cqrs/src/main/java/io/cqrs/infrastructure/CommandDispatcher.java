package io.cqrs.infrastructure;

import io.cqrs.commands.BaseCommand;
import io.cqrs.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);

    void send(BaseCommand command);

}

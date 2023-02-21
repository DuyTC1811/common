package io.cqrs.dispascher;

import io.cqrs.command.ICommand;
import io.cqrs.command.ICommandHandler;
import io.cqrs.query.IQuery;
import io.cqrs.query.IQueryHandler;
import org.springframework.stereotype.Component;

@Component
public class SpringBusImpl implements ISpringBus {
    private final Registry registry;

    public SpringBusImpl(Registry registry) {
        this.registry = registry;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <RESPONSE, REQUEST extends ICommand<RESPONSE>> RESPONSE executeCommand(REQUEST command) {
        ICommandHandler<RESPONSE, REQUEST> ICommandHandler = (ICommandHandler<RESPONSE, REQUEST>) registry.getCmd(command.getClass());
        return ICommandHandler.handler(command);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <RESPONSE, REQUEST extends IQuery<RESPONSE>> RESPONSE executeQuery(REQUEST query) {
        IQueryHandler<RESPONSE, REQUEST> IQueryHandler = (IQueryHandler<RESPONSE, REQUEST>) registry.getQuery(query.getClass());
        return IQueryHandler.handler(query);
    }
}

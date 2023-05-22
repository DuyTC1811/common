package io.cqrs.dispascher;

import io.cqrs.command.ICommand;
import io.cqrs.command.ICommandHandler;
import io.cqrs.model.BaseResponse;
import io.cqrs.model.PageResponse;
import io.cqrs.query.IPage;
import io.cqrs.query.IPageHandler;
import io.cqrs.query.IQuery;
import io.cqrs.query.IQueryHandler;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBusImpl implements ISpringBus {
    private final Registry registry;

    public SpringBusImpl(Registry registry) {
        this.registry = registry;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <RESPONSE, REQUEST extends ICommand<RESPONSE>> BaseResponse<RESPONSE> executeCommand(REQUEST command) {
        ICommandHandler<RESPONSE, REQUEST> commandHandler = registry.getCmd(command.getClass());
        return commandHandler.handler(command);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <RESPONSE, REQUEST extends IQuery<RESPONSE>> BaseResponse<RESPONSE> executeQuery(REQUEST query) {
        IQueryHandler<RESPONSE, REQUEST> queryHandler = registry.getQuery(query.getClass());
        return queryHandler.handler(query);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <RESPONSE, REQUEST extends IPage<RESPONSE>> PageResponse<RESPONSE> executePage(REQUEST pageRequest) {
        IPageHandler<RESPONSE, REQUEST> pageHandler = registry.getPage(pageRequest.getClass());
        return pageHandler.handle(pageRequest);
    }
}

package io.cqrs.dispascher;

import io.cqrs.command.CommandProvider;
import io.cqrs.command.ICommand;
import io.cqrs.command.ICommandHandler;
import io.cqrs.query.IQuery;
import io.cqrs.query.IQueryHandler;
import io.cqrs.query.QueryProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.GenericTypeResolver;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class Registry {

    private final Map<Class<? extends ICommand>, CommandProvider> commandProviderMap = new HashMap<>();
    private final Map<Class<? extends IQuery>, QueryProvider> queryProviderMap = new HashMap<>();

    public Registry(ApplicationContext applicationContext) {
        String[] names = applicationContext.getBeanNamesForType(ICommandHandler.class);
        for (String name : names) {
            registerCommand(applicationContext, name);
        }
        names = applicationContext.getBeanNamesForType(IQueryHandler.class);
        for (String name : names) {
            registerQuery(applicationContext, name);
        }
    }

    @SuppressWarnings("unchecked")
    private void registerCommand(ApplicationContext applicationContext, String name) {
        Class<ICommandHandler<?, ?>> handlerClass = (Class<ICommandHandler<?, ?>>) applicationContext.getType(name);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, ICommandHandler.class);
        Class<? extends ICommand> commandType = (Class<? extends ICommand>) generics[1];
        commandProviderMap.put(commandType, new CommandProvider(applicationContext, handlerClass));
    }

    @SuppressWarnings("unchecked")
    private void registerQuery(ApplicationContext applicationContext, String name) {
        Class<IQueryHandler<?, ?>> handlerClass = (Class<IQueryHandler<?, ?>>) applicationContext.getType(name);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, IQueryHandler.class);
        Class<? extends IQuery> queryType = (Class<? extends IQuery>) generics[1];
        queryProviderMap.put(queryType, new QueryProvider(applicationContext, handlerClass));
    }

    @SuppressWarnings("unchecked")
    <RESPONSE, REQUEST extends ICommand<RESPONSE>> ICommandHandler<RESPONSE, REQUEST> getCmd(Class<REQUEST> commandClass) {
        return commandProviderMap.get(commandClass).get();
    }

    @SuppressWarnings("unchecked")
    <RESPONSE, REQUEST extends IQuery<RESPONSE>> IQueryHandler<RESPONSE, REQUEST> getQuery(Class<REQUEST> commandClass) {
        return queryProviderMap.get(commandClass).get();
    }
}

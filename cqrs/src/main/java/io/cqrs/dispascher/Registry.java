package io.cqrs.dispascher;

import io.cqrs.command.CommandProvider;
import io.cqrs.command.ICommand;
import io.cqrs.command.ICommandHandler;
import io.cqrs.query.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.GenericTypeResolver;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class Registry {

    private final Map<Class<? extends ICommand>, CommandProvider> commandProviderMap = new ConcurrentHashMap<>();
    private final Map<Class<? extends IQuery>, QueryProvider> queryProviderMap = new ConcurrentHashMap<>();
    private final Map<Class<? extends IPage>, PageProvider> pageProviderMap = new ConcurrentHashMap<>();

    public Registry(ApplicationContext applicationContext) {
        Arrays.stream(applicationContext.getBeanNamesForType(ICommandHandler.class)).parallel()
                .forEach(name -> registerCommand(applicationContext, name));

        Arrays.stream(applicationContext.getBeanNamesForType(IQueryHandler.class)).parallel()
                .forEach(name -> registerQuery(applicationContext, name));

        Arrays.stream(applicationContext.getBeanNamesForType(IPageHandler.class)).parallel()
                .forEach(name -> registerPage(applicationContext, name));
    }

    @SuppressWarnings({"unchecked"})
    private void registerPage(ApplicationContext applicationContext, String name) {
        Class<IPageHandler<?, ?>> handlerClass = (Class<IPageHandler<?, ?>>) applicationContext.getType(name);
        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, IPageHandler.class);
        Class<? extends IPage> pageType = (Class<? extends IPage>) generics[1];
        pageProviderMap.put(pageType, new PageProvider(applicationContext, handlerClass));
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
    <RESPONSE, REQUEST extends IPage<RESPONSE>> IPageHandler<RESPONSE, REQUEST> getPage(Class<REQUEST> pageClass) {
        PageProvider provider = pageProviderMap.get(pageClass);
        if (provider == null) {
            throw new IllegalStateException("No IPageHandler registered for " + pageClass.getName());
        }
        return provider.get();
    }

    @SuppressWarnings("unchecked")
    <RESPONSE, REQUEST extends ICommand<RESPONSE>> ICommandHandler<RESPONSE, REQUEST> getCmd(Class<REQUEST> commandClass) {
        CommandProvider provider = commandProviderMap.get(commandClass);
        if (provider == null) {
            throw new IllegalStateException("No ICommandHandler registered for " + commandClass.getName());
        }
        return provider.get();
    }

    @SuppressWarnings("unchecked")
    <RESPONSE, REQUEST extends IQuery<RESPONSE>> IQueryHandler<RESPONSE, REQUEST> getQuery(Class<REQUEST> queryClass) {
        QueryProvider provider = queryProviderMap.get(queryClass);
        if (provider == null) {
            throw new IllegalStateException("No IQueryHandler registered for " + queryClass.getName());
        }
        return provider.get();
    }
}

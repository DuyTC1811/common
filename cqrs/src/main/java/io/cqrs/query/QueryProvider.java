package io.cqrs.query;

import org.springframework.context.ApplicationContext;

public class QueryProvider<H extends IQueryHandler<?, ?>> {
    private final ApplicationContext applicationContext;
    private final Class<H> type;

    public QueryProvider(ApplicationContext applicationContext, Class<H> type) {
        this.applicationContext = applicationContext;
        this.type = type;
    }

    public H get() {
        return applicationContext.getBean(type);
    }
}

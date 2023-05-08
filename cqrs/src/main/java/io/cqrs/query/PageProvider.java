package io.cqrs.query;

import org.springframework.context.ApplicationContext;

public class PageProvider<H extends IPageHandler<?, ?>> {
    private final ApplicationContext applicationContext;
    private final Class<H> type;

    public PageProvider(ApplicationContext applicationContext, Class<H> type) {
        this.applicationContext = applicationContext;
        this.type = type;
    }

    public H get() {
        return applicationContext.getBean(type);
    }
}

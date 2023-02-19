package io.cqrs.query;

public interface IQueryHandler<RESPONSE, REQUEST extends IQuery<RESPONSE>> {
    RESPONSE handle(REQUEST query);
}

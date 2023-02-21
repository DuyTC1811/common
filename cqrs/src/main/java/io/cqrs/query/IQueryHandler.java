package io.cqrs.query;

public interface IQueryHandler<RESPONSE, REQUEST extends IQuery<RESPONSE>> {
    RESPONSE handler(REQUEST query);
}

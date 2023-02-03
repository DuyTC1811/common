package io.cqrs.services.query;

public interface IBaseQueryService<RESPONSE, REQUEST> {
    RESPONSE handler(REQUEST request);
}

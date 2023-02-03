package io.cqrs.services.command;

public interface IBaseCommandService<RESPONSE, REQUEST> {
    RESPONSE handler(REQUEST request);
}

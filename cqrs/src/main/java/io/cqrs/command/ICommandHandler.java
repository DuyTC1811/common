package io.cqrs.command;

import io.cqrs.model.BaseResponse;

public interface ICommandHandler<RESPONSE, REQUEST extends ICommand<RESPONSE>> {
    BaseResponse<RESPONSE> handler(REQUEST command);
}

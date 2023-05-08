package io.cqrs.dispascher;

import io.cqrs.command.ICommand;
import io.cqrs.model.BaseResponse;
import io.cqrs.model.PageResponse;
import io.cqrs.query.IPage;
import io.cqrs.query.IQuery;

public interface ISpringBus {
    <RESPONSE, REQUEST extends ICommand<RESPONSE>> BaseResponse<RESPONSE> executeCommand(REQUEST command);

    <RESPONSE, REQUEST extends IQuery<RESPONSE>> BaseResponse<RESPONSE> executeQuery(REQUEST query);

    <RESPONSE, REQUEST extends IPage<RESPONSE>> PageResponse<RESPONSE> executePage(REQUEST pageResponse);
}

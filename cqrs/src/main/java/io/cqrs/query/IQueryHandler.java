package io.cqrs.query;

import io.cqrs.model.BaseResponse;

public interface IQueryHandler<RESPONSE, REQUEST extends IQuery<RESPONSE>> {
    BaseResponse<RESPONSE> handler(REQUEST query);
}

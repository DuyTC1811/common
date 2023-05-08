package io.cqrs.query;


import io.cqrs.model.PageResponse;

public interface IPageHandler<RESPONSE, REQUEST extends IPage<RESPONSE>> {
    PageResponse<RESPONSE> handle(REQUEST query);
}

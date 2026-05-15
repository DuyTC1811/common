package io.cqrs.controller;

import io.cqrs.dispascher.ISpringBus;
import io.cqrs.model.BaseResponse;
import io.cqrs.query.IQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class QueryController<RESPONSE, REQUEST extends IQuery<RESPONSE>> {
    private final ISpringBus springBus;

    protected QueryController(ISpringBus springBus) {
        this.springBus = springBus;
    }

    public ResponseEntity<BaseResponse<RESPONSE>> execute(REQUEST request) {
        return new ResponseEntity<>(springBus.executeQuery(request), HttpStatus.OK);
    }
}

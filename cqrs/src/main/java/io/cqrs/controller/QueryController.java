package io.cqrs.controller;

import io.cqrs.dispascher.ISpringBus;
import io.cqrs.model.BaseResponse;
import io.cqrs.query.IQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class QueryController<RESPONSE, REQUEST extends IQuery<RESPONSE>> {
    @Autowired
    private ISpringBus springBus;

    protected QueryController() {
    }

    public ResponseEntity<BaseResponse<RESPONSE>> execute(REQUEST request) {
        return new ResponseEntity<>(springBus.executeQuery(request), HttpStatus.OK);
    }
}

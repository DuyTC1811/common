package io.cqrs.controller;

import io.cqrs.dispascher.ISpringBus;
import io.cqrs.query.IQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class QueryController<RESPONSE, REQUEST extends IQuery<RESPONSE>> {
    @Autowired
    private ISpringBus springBus;

    public QueryController() {
    }

    public ResponseEntity<RESPONSE> execute(REQUEST request) {
        return new ResponseEntity<>(springBus.executeQuery(request), HttpStatus.OK);
    }

    protected abstract ResponseEntity<RESPONSE> coordinator(REQUEST request);
}

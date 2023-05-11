package io.cqrs.controller;


import io.cqrs.dispascher.ISpringBus;
import io.cqrs.model.PageResponse;
import io.cqrs.query.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class PageController<RESPONSE, REQUEST extends IPage<RESPONSE>> {
    @Autowired
    private ISpringBus springBus;

    protected PageController() {
    }

    public ResponseEntity<PageResponse<RESPONSE>> execute(REQUEST request) {
        return new ResponseEntity<>(springBus.executePage(request), HttpStatus.OK);
    }

    protected abstract ResponseEntity<PageResponse<RESPONSE>> executePage(REQUEST request);
}

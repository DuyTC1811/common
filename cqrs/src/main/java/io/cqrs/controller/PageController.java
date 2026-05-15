package io.cqrs.controller;


import io.cqrs.dispascher.ISpringBus;
import io.cqrs.model.PageResponse;
import io.cqrs.query.IPage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class PageController<RESPONSE, REQUEST extends IPage<RESPONSE>> {
    private final ISpringBus springBus;

    protected PageController(ISpringBus springBus) {
        this.springBus = springBus;
    }

    public ResponseEntity<PageResponse<RESPONSE>> execute(REQUEST request) {
        return new ResponseEntity<>(springBus.executePage(request), HttpStatus.OK);
    }
}

package io.cqrs.controller;

import io.cqrs.command.ICommand;
import io.cqrs.dispascher.ISpringBus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public abstract class CommandController<RESPONSE, REQUEST extends ICommand<RESPONSE>> {

    private final ISpringBus springBus;

    public CommandController(ISpringBus springBus) {
        this.springBus = springBus;
    }

    public ResponseEntity<RESPONSE> execute(REQUEST request) {
        return new ResponseEntity<>(springBus.executeCommand(request), HttpStatus.OK);
    }

    protected abstract ResponseEntity<RESPONSE> coordinator(REQUEST request);
}

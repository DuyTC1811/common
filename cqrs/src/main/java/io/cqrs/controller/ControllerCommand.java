package io.cqrs.controller;

import io.cqrs.command.ICommand;
import io.cqrs.dispascher.ISpringBus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class ControllerCommand<RESPONSE, REQUEST extends ICommand<RESPONSE>> implements IControllerCommand<RESPONSE, REQUEST> {
    private ISpringBus springBus;

    public ControllerCommand() {
    }

    protected ControllerCommand(ISpringBus springBus) {
        this.springBus = springBus;
    }

    public ResponseEntity<RESPONSE> execute(REQUEST request) {
        return new ResponseEntity<>(springBus.executeCommand(request), HttpStatus.OK);
    }
}

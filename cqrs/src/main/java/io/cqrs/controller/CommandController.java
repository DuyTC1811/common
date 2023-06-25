package io.cqrs.controller;

import io.cqrs.command.ICommand;
import io.cqrs.dispascher.ISpringBus;
import io.cqrs.model.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class CommandController<RESPONSE, REQUEST extends ICommand<RESPONSE>> {
    @Autowired
    private ISpringBus springBus;

    protected CommandController() {
    }

    public ResponseEntity<BaseResponse<RESPONSE>> execute(REQUEST request) {
        return new ResponseEntity<>(springBus.executeCommand(request), HttpStatus.OK);
    }

}

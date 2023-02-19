package io.cqrs.controller;

import org.springframework.http.ResponseEntity;

public interface IControllerCommand<RESPONSE, REQUEST> {
    ResponseEntity<RESPONSE> coordinator(REQUEST request);
}

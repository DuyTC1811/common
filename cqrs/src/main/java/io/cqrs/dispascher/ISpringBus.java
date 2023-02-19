package io.cqrs.dispascher;

import io.cqrs.command.ICommand;
import io.cqrs.query.IQuery;

public interface ISpringBus {
    <RESPONSE, REQUEST extends ICommand<RESPONSE>> RESPONSE executeCommand(REQUEST command);

    <RESPONSE, REQUEST extends IQuery<RESPONSE>> RESPONSE executeQuery(REQUEST query);
}

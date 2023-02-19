package io.cqrs.query;

public interface IQueryHandler<R, C extends IQuery<R>> {
    R handle(C query);
}

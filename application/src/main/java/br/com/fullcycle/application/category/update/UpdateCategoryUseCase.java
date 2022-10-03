package br.com.fullcycle.application.category.update;

import br.com.fullcycle.application.UseCase;
import br.com.fullcycle.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class UpdateCategoryUseCase extends UseCase<
        UpdateCategoryCommand,
        Either<Notification, UpdateCategoryOutput>> {

}

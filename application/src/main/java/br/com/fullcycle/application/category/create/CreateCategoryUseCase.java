package br.com.fullcycle.application.category.create;

import br.com.fullcycle.application.UseCase;
import br.com.fullcycle.domain.validation.handler.Notification;
import io.vavr.control.Either;

public abstract class CreateCategoryUseCase extends
        UseCase<CreateCategoryCommand, Either<Notification, CreateCategoryOutput>> {

}

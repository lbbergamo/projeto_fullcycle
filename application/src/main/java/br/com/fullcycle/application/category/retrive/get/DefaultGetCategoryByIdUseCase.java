package br.com.fullcycle.application.category.retrive.get;

import br.com.fullcycle.domain.category.CategoryGateway;
import br.com.fullcycle.domain.category.CategoryID;
import br.com.fullcycle.domain.exceptions.DomainException;
import br.com.fullcycle.domain.validation.Error;

import java.util.Objects;
import java.util.function.Supplier;

public class DefaultGetCategoryByIdUseCase extends GetCategoryByIdUseCase {

    private final CategoryGateway categoryGateway;

    public DefaultGetCategoryByIdUseCase(CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public CategoryOutput execute(final String anIN) {
        final var anCategoryId = CategoryID.from(anIN);

        return this.categoryGateway.findById(anCategoryId)
                .map(CategoryOutput::from)
                .orElseThrow(notFound(anCategoryId));
    }

    private Supplier<DomainException> notFound(final CategoryID anId) {
        return () ->
                DomainException.with(
                        new Error("Category with %s was not found".formatted(anId.getValue())));
    }
}

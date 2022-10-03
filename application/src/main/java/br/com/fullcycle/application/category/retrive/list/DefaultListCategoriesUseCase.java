package br.com.fullcycle.application.category.retrive.list;

import br.com.fullcycle.domain.category.CategoryGateway;
import br.com.fullcycle.domain.category.CategorySearchQuery;
import br.com.fullcycle.domain.pagination.Pagination;

import java.util.Objects;

public class DefaultListCategoriesUseCase extends ListCategoriesUseCase {

    private final CategoryGateway categoryGateway;


    public DefaultListCategoriesUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public Pagination<CategoryListOutPut> execute(final CategorySearchQuery aQuery) {
        return this.categoryGateway.findAll(aQuery).map(CategoryListOutPut::from);
    }
}

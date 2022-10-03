package br.com.fullcycle.application.category.retrive.list;

import br.com.fullcycle.application.UseCase;
import br.com.fullcycle.domain.category.CategorySearchQuery;
import br.com.fullcycle.domain.pagination.Pagination;

public abstract class ListCategoriesUseCase
        extends UseCase<CategorySearchQuery, Pagination<CategoryListOutPut>> {
}

package br.com.fullcycle.application.category.create;

import br.com.fullcycle.domain.category.Category;
import br.com.fullcycle.domain.category.CategoryID;

public record CreateCategoryOutput(CategoryID id) {

    public static CreateCategoryOutput from(final Category aCategory) {
        return new CreateCategoryOutput(aCategory.getId());
    }
}

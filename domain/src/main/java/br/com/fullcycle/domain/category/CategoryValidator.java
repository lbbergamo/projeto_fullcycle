package br.com.fullcycle.domain.category;

import br.com.fullcycle.domain.validation.Error;
import br.com.fullcycle.domain.validation.ValidationHandler;
import br.com.fullcycle.domain.validation.Validator;

public class CategoryValidator extends Validator {

    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 255;
    private final Category category;

    public CategoryValidator(final Category aCategory,
                             ValidationHandler aHandler) {
        super(aHandler);
        this.category = aCategory;
    }

    @Override
    public void validate() {
        checkNameConstrains();
    }

    private void checkNameConstrains() {
        final var name = this.category.getName();
        if (name == null) {
            this.validationHandler().append(
                    new Error("'name' should not be null"));
            return;
        }
        if (name.isBlank()) {
            this.validationHandler().append(
                    new Error("'name' should not be empty"));
            return;
        }

        final int length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(
                    new Error("'name' must be between 3 and 255 characters"));
        }
    }
}

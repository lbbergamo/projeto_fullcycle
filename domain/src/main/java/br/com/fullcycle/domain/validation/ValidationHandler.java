package br.com.fullcycle.domain.validation;

import java.util.List;

public interface ValidationHandler {

    List<Error> getErrors();

    ValidationHandler append(Error anError);

    ValidationHandler append(ValidationHandler anHandler);

    ValidationHandler validate(Validation aValidation);

    default Error firstError() {
        if (getErrors() != null && !getErrors().isEmpty()) {
            return getErrors().get(0);
        }
        return null;
    }

    default boolean hasError() {
        return getErrors() != null && !(getErrors().size() == 0) && !getErrors().isEmpty();
    }

    interface Validation {
        void validate();
    }
}

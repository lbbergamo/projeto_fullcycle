package br.com.fullcycle.domain.category;

import br.com.fullcycle.domain.exceptions.DomainException;
import br.com.fullcycle.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void givenAValidParams_whenCallNewCategory_thenInstantiateACategory() {
        final var expectName = "Filmes";
        final var expectDescription = "A Categoria mais assistida";
        final var expectedIsActive = true;

        final var actualCategory = Category.newCategory(expectName, expectDescription, expectedIsActive);

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectName, actualCategory.getName());
        Assertions.assertEquals(expectDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectName = null;
        final var expectDescription = "A Categoria mais assistida";
        final var expectedIsActive = true;

        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        final var actualCategory = Category.newCategory(expectName,
                expectDescription,
                expectedIsActive);

        final var actualException = Assertions.assertThrows(
                DomainException.class,
                () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException
                .getErrors()
                .get(0)
                .message());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectName = " ";
        final var expectDescription = "A Categoria mais assistida";
        final var expectedIsActive = true;

        final var expectedErrorMessage = "'name' should not be empty";
        final var expectedErrorCount = 1;

        final var actualCategory = Category.newCategory(expectName,
                expectDescription,
                expectedIsActive);

        final var actualException = Assertions.assertThrows(
                DomainException.class,
                () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException
                .getErrors()
                .get(0)
                .message());
    }

    @Test
    public void givenAnInvalidNameLengthLessThan3_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectName = "Te ";
        final var expectDescription = "A Categoria mais assistida";
        final var expectedIsActive = true;

        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorCount = 1;

        final var actualCategory = Category.newCategory(expectName,
                expectDescription,
                expectedIsActive);

        final var actualException = Assertions.assertThrows(
                DomainException.class,
                () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException
                .getErrors()
                .get(0)
                .message());
    }

    @Test
    public void givenAnInvalidNameLengthLessThan255_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final var expectDescription = "A Categoria mais assistida";
        final var expectedIsActive = true;

        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";
        final var expectedErrorCount = 1;

        final String expectName = """
                    O que temos que ter sempre em mente é que a criticidade dos dados
                    em questão representa uma aberturapara a melhoria dos equipamentos pré-especificados. É
                    importante questionar o quanto o consenso sobre a utilização da orientação a objeto facilita
                    a criação da terceirização dos serviços. Acima de tudo, é fundamental ressaltar que a complexidade
                    computacional otimiza o uso dos processadores do levantamento das variáveis envolvidas.
                    Todavia, o novo modelo computacional aqui preconizado conduz a um melhor balancemanto de
                    carga das formas de ação. No nível organizacional,
                    a consulta aos diversos sistemas agrega valor ao serviço prestado da
                    utilização dos serviços nas nuvens.
                """;

        final var actualCategory = Category.newCategory(expectName,
                expectDescription,
                expectedIsActive);

        final var actualException = Assertions.assertThrows(
                DomainException.class,
                () -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException
                .getErrors()
                .get(0)
                .message());
    }

    @Test
    public void givenAnInvalidEmptyDescription_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectName = "Teste";
        final var expectDescription = " ";
        final var expectedIsActive = true;

        final var expectedErrorMessage = "'Description' should not be empty";
        final var expectedErrorCount = 1;

        final var actualCategory = Category.newCategory(expectName,
                expectDescription,
                expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectName, actualCategory.getName());
        Assertions.assertEquals(expectDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidFalseIsActive_whenCallNewCategoryAndValidate_thenShouldReceiveError() {
        final String expectName = "Teste";
        final var expectDescription = "A Categoria mais assistida";
        final var expectedIsActive = false;

        final var actualCategory = Category.newCategory(expectName,
                expectDescription,
                expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectName, actualCategory.getName());
        Assertions.assertEquals(expectDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNotNull(actualCategory.getDeletedAt());
    }

    @Test
    public void givenAValidActiveCategory_whenCallDeactivate_thenReturnCategoryInactive() {
        final var expectName = "Filmes";
        final var expectDescription = "A Categoria mais assistida";
        final var expectedIsActive = false;

        final var aCategory = Category.newCategory(expectName, expectDescription, true);

        final var updatedAt = aCategory.getUpdatedAt();
        Assertions.assertNull(aCategory.getDeletedAt());
        Assertions.assertTrue(aCategory.isActive());

        final var actualCategory = aCategory.deactivate();

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNotNull(actualCategory.getDeletedAt());
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectName, actualCategory.getName());
        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertFalse(actualCategory.isActive());
    }

    @Test
    public void givenAValidInactiveCategory_whenCallActive_thenReturnCategoryActivated() {
        final var expectName = "Filmes";
        final var expectDescription = "A Categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory(expectName, expectDescription, false);

        final var updatedAt = aCategory.getUpdatedAt();
        Assertions.assertNotNull(aCategory.getDeletedAt());
        Assertions.assertFalse(aCategory.isActive());

        final var actualCategory = aCategory.activate();

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertNull(actualCategory.getDeletedAt());
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectName, actualCategory.getName());
        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertTrue(actualCategory.isActive());
    }

    @Test
    public void givenAValidCategory_whenCallUpdate_thenReturnCategoryUpdated() {
        final var expectName = "Filmes";
        final var expectDescription = "A Categoria mais assistida";
        final var expectedIsActive = true;

        final var aCategory = Category.newCategory(
                "Film",
                "Category Description",
                true);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));

        final var updatedAt = aCategory.getUpdatedAt();
        final var createdAt = aCategory.getCreatedAt();

        final var actualCategory = aCategory.update(expectName, expectDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());

        Assertions.assertNull(actualCategory.getDeletedAt());
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectName, actualCategory.getName());
        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
        Assertions.assertTrue(actualCategory.isActive());
    }


    @Test
    public void givenAValidCategory_whenCallUpdateToInactive_thenReturnCategoryUpdated() {
        final var expectName = "Filmes";
        final var expectDescription = "A Categoria mais assistida";
        final var expectedIsActive = false;

        final var aCategory = Category.newCategory(
                "Film",
                "Category Description",
                true);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var updatedAt = aCategory.getUpdatedAt();
        final var createdAt = aCategory.getCreatedAt();

        final var actualCategory = aCategory.update(expectName, expectDescription, expectedIsActive);

        Assertions.assertDoesNotThrow(() -> actualCategory.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());

        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectName, actualCategory.getName());
        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNotNull(actualCategory.getDeletedAt());
        Assertions.assertFalse(actualCategory.isActive());
    }

    @Test
    public void givenAValidCategory_whenCallUpdateWithInvalidParams_thenReturnCategoryUpdated() {
        final String expectName = null;
        final var expectDescription = "A Categoria mais assistida";
        final var expectedIsActive = false;

        final var aCategory = Category.newCategory(
                "Film",
                "Category Description",
                true);

        Assertions.assertDoesNotThrow(() -> aCategory.validate(new ThrowsValidationHandler()));
        Assertions.assertTrue(aCategory.isActive());
        Assertions.assertNull(aCategory.getDeletedAt());

        final var updatedAt = aCategory.getUpdatedAt();
        final var createdAt = aCategory.getCreatedAt();

        final var actualCategory = aCategory.update(expectName, expectDescription, expectedIsActive);

        Assertions.assertEquals(createdAt, actualCategory.getCreatedAt());

        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertEquals(expectName, actualCategory.getName());
        Assertions.assertEquals(aCategory.getId(), actualCategory.getId());
        Assertions.assertEquals(expectDescription, actualCategory.getDescription());
        Assertions.assertEquals(expectedIsActive, actualCategory.isActive());
        Assertions.assertNotNull(actualCategory.getCreatedAt());
        Assertions.assertTrue(actualCategory.getUpdatedAt().isAfter(updatedAt));
        Assertions.assertNotNull(actualCategory.getUpdatedAt());
        Assertions.assertNotNull(actualCategory.getDeletedAt());
        Assertions.assertFalse(actualCategory.isActive());
    }
}

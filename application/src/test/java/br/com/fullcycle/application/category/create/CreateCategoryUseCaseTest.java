package br.com.fullcycle.application.category.create;


import br.com.fullcycle.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class CreateCategoryUseCaseTest {

    @InjectMocks
    private DefaultCreateCategoryUseCase useCase;

    @Mock
    private CategoryGateway categoryGateway;
    // 1. Teste do caminho feliz
    // 2. Teste passando uma propriedade invalida
    // 3. Teste criando uma categoria inativa
    // 4. Teste simulando um erro generico vindo do gateway

    @BeforeEach
    void cleanUP() {
        Mockito.reset(categoryGateway);
    }

    @Test
    public void giveAValidCommand_whenCallsCreateCategory_shouldReturnCategoryId() {

        final var expectedName = "FIlmes";
        final var expectedDescription = "A Categoria mais assistida";
        final var expectedIsActive = true;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.get().id());

        verify(categoryGateway, times(1))
                .create(argThat(aCategory -> Objects.equals(expectedName, aCategory.getName())
                                             && Objects.equals(expectedDescription, aCategory.getDescription())
                                             && Objects.equals(expectedIsActive, aCategory.isActive())
                                             && Objects.nonNull(aCategory.getId())
                                             && Objects.nonNull(aCategory.getCreatedAt())
                                             && Objects.nonNull(aCategory.getUpdatedAt())
                                             && Objects.isNull(aCategory.getDeletedAt())
                ));
    }

    @Test
    public void giveAValidCommand_whenCallsCreateCategory_thenShouldReturnDomainException() {

        final String expectedName = null;
        final var expectedDescription = "A Categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "'name' should not be null";
        final var expectedErrorCount = 1;

        final var aCommand =
                CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        verify(categoryGateway, times(0)).create(any());
    }


    @Test
    public void giveAValidCommandWithInactiveCategory_whenCallsCreateCategory_shouldReturnInactiveCategoryId() {

        final var expectedName = "FIlmes";
        final var expectedDescription = "A Categoria mais assistida";
        final var expectedIsActive = false;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.create(any())).thenAnswer(returnsFirstArg());

        final var actualOutput = useCase.execute(aCommand);

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.get().id());

        verify(categoryGateway, times(1))
                .create(argThat(aCategory -> Objects.equals(expectedName, aCategory.getName())
                                             && Objects.equals(expectedDescription, aCategory.getDescription())
                                             && Objects.equals(expectedIsActive, aCategory.isActive())
                                             && Objects.nonNull(aCategory.getId())
                                             && Objects.nonNull(aCategory.getCreatedAt())
                                             && Objects.nonNull(aCategory.getUpdatedAt())
                                             && Objects.nonNull(aCategory.getDeletedAt())
                ));
    }

    @Test
    public void giveAValidCommandWithInactiveCategory_whenGatewayThrowRandomException_shouldReturnException() {

        final var expectedName = "FIlmes";
        final var expectedDescription = "A Categoria mais assistida";
        final var expectedIsActive = true;
        final var expectedErrorMessage = "Gateway Error";
        final var expectedErrorCount = 1;

        final var aCommand = CreateCategoryCommand.with(expectedName, expectedDescription, expectedIsActive);

        when(categoryGateway.create(any())).thenThrow(new IllegalStateException(expectedErrorMessage));

        final var notification = useCase.execute(aCommand).getLeft();

        Assertions.assertEquals(expectedErrorCount, notification.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, notification.firstError().message());

        verify(categoryGateway, times(1))
                .create(argThat(aCategory -> Objects.equals(expectedName, aCategory.getName())
                                             && Objects.equals(expectedDescription, aCategory.getDescription())
                                             && Objects.equals(expectedIsActive, aCategory.isActive())
                                             && Objects.nonNull(aCategory.getId())
                                             && Objects.nonNull(aCategory.getCreatedAt())
                                             && Objects.nonNull(aCategory.getUpdatedAt())
                                             && Objects.isNull(aCategory.getDeletedAt())
                ));
    }

}

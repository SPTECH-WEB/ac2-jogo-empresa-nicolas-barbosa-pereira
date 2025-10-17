package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class AvaliacaoArgumentProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(4.5, "EXCELENTE"),
                Arguments.of(5.0, "EXCELENTE"),
                Arguments.of(4.0, "BOM"),
                Arguments.of(3.5, "BOM"),
                Arguments.of(3.0, "REGULAR"),
                Arguments.of(2.5, "REGULAR"),
                Arguments.of(2.0, "RUIM"),
                Arguments.of(1.0, "RUIM")
        );
    }
}
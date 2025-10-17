package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class RemoverJogoCodigoInvalidoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("Resident Evil 2", "Jogo não encontrado"),
                Arguments.of("Resident Evil 1", "Jogo não encontrado"),
                Arguments.of("Resident Evil 3", "Jogo não encontrado")
        );
    }
}


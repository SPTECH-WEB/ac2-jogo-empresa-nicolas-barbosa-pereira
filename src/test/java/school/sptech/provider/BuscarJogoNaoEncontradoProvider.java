package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class BuscarJogoNaoEncontradoProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of("HALO"),
                Arguments.of("MARIO"),
                Arguments.of("ZELDA"),
                Arguments.of("SEKIRO"),
                Arguments.of("DARKSOULS"),
                Arguments.of("GODOFWAR"),
                Arguments.of("FIFA21")
        );
    }
}

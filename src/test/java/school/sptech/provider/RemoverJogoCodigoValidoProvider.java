package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.JogoFactory;

import java.time.LocalDate;
import java.util.stream.Stream;

public class RemoverJogoCodigoValidoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of("GTA5", JogoFactory.build("GTA5", "Grand Theft Auto V", "Ação", 199.99, 4.8, LocalDate.of(2013, 9, 17))),
                Arguments.of("CP2077", JogoFactory.build("CP2077", "Cyberpunk 2077", "RPG", 249.90, 3.5, LocalDate.of(2020, 12, 10))),
                Arguments.of("BOTW", JogoFactory.build("BOTW", "The Legend of Zelda: Breath of the Wild", "Aventura", 299.90, 4.9, LocalDate.of(2017, 3, 3)))
        );
    }
}

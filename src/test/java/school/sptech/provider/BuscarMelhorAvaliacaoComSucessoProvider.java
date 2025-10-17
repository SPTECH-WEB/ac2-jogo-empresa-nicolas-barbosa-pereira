package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.JogoFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class BuscarMelhorAvaliacaoComSucessoProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(
                        List.of(
                                JogoFactory.build("GTA5", "Grand Theft Auto V", "Ação", 199.99, 4.8, LocalDate.of(2013, 9, 17)),
                                JogoFactory.build("CP2077", "Cyberpunk 2077", "RPG", 249.90, 3.5, LocalDate.of(2020, 12, 10)),
                                JogoFactory.build("BOTW", "The Legend of Zelda: Breath of the Wild", "Aventura", 299.90, 4.9, LocalDate.of(2017, 3, 3))
                        ),
                        JogoFactory.build("BOTW", "The Legend of Zelda: Breath of the Wild", "Aventura", 299.90, 4.9, LocalDate.of(2017, 3, 3))
                ),
                Arguments.of(
                        List.of(
                                JogoFactory.build("HADES", "Hades", "Rogue-like", 89.99, 4.9, LocalDate.of(2020, 9, 17)),
                                JogoFactory.build("RDR2", "Red Dead Redemption 2", "Ação", 219.90, 4.8, LocalDate.of(2018, 10, 26)),
                                JogoFactory.build("TLOU", "The Last of Us", "Ação", 149.90, 4.7, LocalDate.of(2013, 6, 14))
                        ),
                        JogoFactory.build("HADES", "Hades", "Rogue-like", 89.99, 4.9, LocalDate.of(2020, 9, 17))
                )
        );
    }
}

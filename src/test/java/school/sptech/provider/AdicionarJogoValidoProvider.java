package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.util.JogoUtil;

import java.time.LocalDate;
import java.util.stream.Stream;

public class AdicionarJogoValidoProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(JogoUtil.configurarJogo("GTA5", "Grand Theft Auto V", "Ação", 199.99, 4.8, LocalDate.of(2013, 9, 17))),
                Arguments.of(JogoUtil.configurarJogo("CP2077", "Cyberpunk 2077", "RPG", 249.90, 3.5, LocalDate.of(2020, 12, 10))),
                Arguments.of(JogoUtil.configurarJogo("BOTW", "The Legend of Zelda: Breath of the Wild", "Aventura", 299.90, 4.9, LocalDate.of(2017, 3, 3))),
                Arguments.of(JogoUtil.configurarJogo("RDR2", "Red Dead Redemption 2", "Ação", 199.99, 4.7, LocalDate.of(2018, 10, 26))),
                Arguments.of(JogoUtil.configurarJogo("TLOU2", "The Last of Us Part II", "Ação", 199.99, 4.6, LocalDate.of(2020, 6, 19))),
                Arguments.of(JogoUtil.configurarJogo("FF7R", "Final Fantasy VII Remake", "RPG", 249.90, 4.5, LocalDate.of(2020, 4, 10))),
                Arguments.of(JogoUtil.configurarJogo("GOW", "God of War", "Ação", 79.90, 4.9, LocalDate.of(2018, 4, 20)))
        );
    }
}

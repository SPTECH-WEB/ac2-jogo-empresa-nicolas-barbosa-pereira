package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import school.sptech.factory.JogoFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

public class BuscarMelhorAvaliacaoEmpateProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        return Stream.of(
                Arguments.of(
                        List.of(
                                JogoFactory.build("GTA5", "Grand Theft Auto V", "Ação", 199.99, 4.9, LocalDate.of(2013, 9, 17)),
                                JogoFactory.build("RDR2", "Red Dead Redemption 2", "Ação", 219.90, 4.9, LocalDate.of(2018, 10, 26))
                        ),
                        JogoFactory.build("RDR2", "Red Dead Redemption 2", "Ação", 219.90, 4.9, LocalDate.of(2018, 10, 26))
                )
        );
    }
}
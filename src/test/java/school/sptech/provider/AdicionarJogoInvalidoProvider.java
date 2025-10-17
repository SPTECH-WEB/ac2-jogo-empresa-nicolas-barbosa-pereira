package school.sptech.provider;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.time.LocalDate;
import java.util.stream.Stream;

import static school.sptech.util.JogoUtil.configurarJogo;

public class AdicionarJogoInvalidoProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(

                // Jogo nulo
                Arguments.of(null, "Jogo não pode ser nulo"),

                // Código inválido
                Arguments.of(configurarJogo(null, "Red Dead Redemption 2", "Ação", 199.99, 4.9, LocalDate.of(2018, 10, 26)), "Código do jogo não pode ser nulo ou vazio"),
                Arguments.of(configurarJogo("", "Red Dead Redemption 2", "Ação", 199.99, 4.9, LocalDate.of(2018, 10, 26)), "Código do jogo não pode ser nulo ou vazio"),

                // Nome inválido
                Arguments.of(configurarJogo("RDR2", null, "Ação", 199.99, 4.9, LocalDate.of(2018, 10, 26)), "Nome do jogo não pode ser nulo ou vazio"),
                Arguments.of(configurarJogo("RDR2", "", "Ação", 199.99, 4.9, LocalDate.of(2018, 10, 26)), "Nome do jogo não pode ser nulo ou vazio"),

                // Gênero inválido
                Arguments.of(configurarJogo("RDR2", "Red Dead Redemption 2", null, 199.99, 4.9, LocalDate.of(2018, 10, 26)), "Gênero do jogo não pode ser nulo ou vazio"),
                Arguments.of(configurarJogo("RDR2", "Red Dead Redemption 2", "", 199.99, 4.9, LocalDate.of(2018, 10, 26)), "Gênero do jogo não pode ser nulo ou vazio"),

                // Preço inválido
                Arguments.of(configurarJogo("RDR2", "Red Dead Redemption 2", "Ação", 0.0, 4.9, LocalDate.of(2018, 10, 26)), "Preço do jogo não pode ser nulo ou menor ou igual a zero"),
                Arguments.of(configurarJogo("RDR2", "Red Dead Redemption 2", "Ação", -100.0, 4.9, LocalDate.of(2018, 10, 26)), "Preço do jogo não pode ser nulo ou menor ou igual a zero"),

                // Avaliação inválida
                Arguments.of(configurarJogo("RDR2", "Red Dead Redemption 2", "Ação", 199.99, 6.0, LocalDate.of(2018, 10, 26)), "Avaliação do jogo não pode ser nulo ou menor que zero ou maior que cinco"),
                Arguments.of(configurarJogo("RDR2", "Red Dead Redemption 2", "Ação", 199.99, -1.0, LocalDate.of(2018, 10, 26)), "Avaliação do jogo não pode ser nulo ou menor que zero ou maior que cinco"),

                // Data de lançamento inválida
                Arguments.of(configurarJogo("RDR2", "Red Dead Redemption 2", "Ação", 199.99, 4.9, LocalDate.now().plusDays(1)), "Data de lançamento do jogo não pode ser maior que a data atual"),
                Arguments.of(configurarJogo("RDR2", "Red Dead Redemption 2", "Ação", 199.99, 4.9, null), "Data de lançamento do jogo não pode ser nula")
        );
    }


}

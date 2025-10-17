package school.sptech.logica;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Jogo;
import school.sptech.provider.AvaliacaoArgumentProvider;
import school.sptech.util.JogoUtil;

import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("3. TESTE DE AVALIAÇÃO - JOGO")
class JogoAvaliacaoTest {

    @ParameterizedTest
    @ArgumentsSource(AvaliacaoArgumentProvider.class)
    @DisplayName("3.1. Deve retornar a descrição correta com base na avaliação")
    void testGetAvaliacaoDescricao(Double avaliacao, String descricaoEsperada) throws Exception {
        // Usa reflection para criar uma instância de Jogo
        Jogo jogo = Jogo.class.getDeclaredConstructor().newInstance();

        // Usa reflection para definir o valor do campo 'avaliacao'
        JogoUtil.configurarCampo(jogo, "avaliacao", avaliacao);

        // Usa reflection para chamar o método 'getAvaliacaoDescricao'
        Method methodGetAvaliacaoDescricao = Jogo.class.getDeclaredMethod("getAvaliacaoDescricao");
        String descricaoRetornada = (String) methodGetAvaliacaoDescricao.invoke(jogo);

        // Compara a descrição retornada com a esperada
        assertEquals(descricaoEsperada, descricaoRetornada,
                "A descrição retornada não está correta para a avaliação: " + avaliacao);
    }
}

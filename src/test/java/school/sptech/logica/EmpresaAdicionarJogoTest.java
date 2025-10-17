package school.sptech.logica;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Empresa;
import school.sptech.Jogo;
import school.sptech.exception.JogoInvalidoException;
import school.sptech.factory.EmpresaFactory;
import school.sptech.provider.AdicionarJogoInvalidoProvider;
import school.sptech.provider.AdicionarJogoValidoProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("4. TESTE DE ADICIONAR JOGO - EMPRESA")
class EmpresaAdicionarJogoTest {

    @ParameterizedTest
    @ArgumentsSource(AdicionarJogoValidoProvider.class)
    @DisplayName("4.1. Quando o jogo é válido, deve adicioná-lo corretamente à lista de jogos")
    void testAdicionarJogoComSucesso(Jogo jogoValido) throws Exception {
        // Usa a EmpresaFactory para criar uma instância de Empresa
        Empresa empresa = (Empresa) EmpresaFactory.build("Empresa Teste", new ArrayList<>());

        // Usa reflection para chamar o método adicionarJogo
        Method methodAdicionarJogo = Empresa.class.getDeclaredMethod("adicionarJogo", Jogo.class);
        methodAdicionarJogo.invoke(empresa, jogoValido);

        // Usa reflection para verificar se o jogo foi adicionado à lista de jogos
        Method methodGetJogos = Empresa.class.getDeclaredMethod("getJogos");
        List<Jogo> jogos = (List<Jogo>) methodGetJogos.invoke(empresa);

        assertTrue(jogos.contains(jogoValido), "O jogo deve ser adicionado com sucesso à lista de jogos.");
    }

    @ParameterizedTest
    @ArgumentsSource(AdicionarJogoInvalidoProvider.class)
    @DisplayName("4.2. Quando o jogo é inválido, deve lançar ArgumentoInvalidoException")
    void testAdicionarJogoComFalha(Jogo jogoInvalido, String mensagemEsperada) throws Exception {
        // Usa a EmpresaFactory para criar uma instância de Empresa
        Empresa empresa = (Empresa) EmpresaFactory.build("Empresa Teste", List.of());

        // Usa reflection para chamar o método adicionarJogo
        Method methodAdicionarJogo = Empresa.class.getDeclaredMethod("adicionarJogo", Jogo.class);

        // Captura a exceção e verifica se é ArgumentoInvalidoException
        InvocationTargetException invocationTargetException = assertThrows(InvocationTargetException.class,
                () -> methodAdicionarJogo.invoke(empresa, jogoInvalido),
                "Deveria lançar uma InvocationTargetException com ArgumentoInvalidoException como causa."
        );

        // Obtém a causa da InvocationTargetException
        Throwable causa = invocationTargetException.getCause();

        assertTrue(causa instanceof JogoInvalidoException, "A causa deve ser JogoInvalidoException.");

    }
}

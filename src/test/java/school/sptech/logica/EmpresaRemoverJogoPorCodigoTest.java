package school.sptech.logica;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Empresa;
import school.sptech.Jogo;
import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.JogoNaoEncontradoException;
import school.sptech.factory.EmpresaFactory;
import school.sptech.provider.RemoverJogoCodigoInvalidoProvider;
import school.sptech.provider.RemoverJogoCodigoNuloVazioProvider;
import school.sptech.provider.RemoverJogoCodigoValidoProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("6. TESTE DE REMOVER JOGO POR CÓDIGO - EMPRESA")
class EmpresaRemoverJogoPorCodigoTest {

    @ParameterizedTest
    @ArgumentsSource(RemoverJogoCodigoValidoProvider.class)
    @DisplayName("6.1. Quando o código do jogo é válido, deve removê-lo corretamente da lista de jogos")
    void testRemoverJogoComSucesso(String codigoValido, Jogo jogoRemovidoEsperado) throws Exception {
        // Usa a EmpresaFactory para criar uma instância de Empresa com jogos
        List<Jogo> jogos = new ArrayList<>(List.of(jogoRemovidoEsperado));
        Empresa empresa = (Empresa) EmpresaFactory.build("Empresa Teste", jogos);

        // Usa reflection para chamar o método removerJogoPorCodigo
        Method methodRemoverJogo = Empresa.class.getDeclaredMethod("removerJogoPorCodigo", String.class);
        methodRemoverJogo.invoke(empresa, codigoValido);

        // Usa reflection para verificar se o jogo foi removido da lista de jogos
        Method methodGetJogos = Empresa.class.getDeclaredMethod("getJogos");
        List<Jogo> jogosAtualizados = (List<Jogo>) methodGetJogos.invoke(empresa);

        assertFalse(jogosAtualizados.contains(jogoRemovidoEsperado), "O jogo deve ser removido com sucesso da lista de jogos.");
    }

    @ParameterizedTest
    @ArgumentsSource(RemoverJogoCodigoInvalidoProvider.class)
    @DisplayName("6.2. Quando o jogo não for encontrado pelo código, deve lançar JogoNaoEncontradoException")
    void testRemoverJogoNaoEncontrado(String codigoInvalido, String mensagemEsperada) throws Exception {
        Empresa empresa = (Empresa) EmpresaFactory.build("Empresa Teste", List.of());

        Method methodRemoverJogo = Empresa.class.getDeclaredMethod("removerJogoPorCodigo", String.class);

        InvocationTargetException invocationTargetException = assertThrows(InvocationTargetException.class,
                () -> methodRemoverJogo.invoke(empresa, codigoInvalido),
                "Deveria lançar uma InvocationTargetException com JogoNaoEncontradoException como causa."
        );

        Throwable causa = invocationTargetException.getCause();
        assertTrue(causa instanceof JogoNaoEncontradoException, "A causa deve ser JogoNaoEncontradoException.");
    }

    @ParameterizedTest
    @ArgumentsSource(RemoverJogoCodigoNuloVazioProvider.class)
    @DisplayName("6.3. Quando o código é nulo, vazio ou em branco, deve lançar ArgumentoInvalidoException")
    void testRemoverJogoComCodigoInvalido(String codigoInvalido, String mensagemEsperada) throws Exception {
        // Usa a EmpresaFactory para criar uma instância de Empresa sem jogos
        Empresa empresa = (Empresa) EmpresaFactory.build("Empresa Teste", List.of());

        // Usa reflection para chamar o método removerJogoPorCodigo
        Method methodRemoverJogo = Empresa.class.getDeclaredMethod("removerJogoPorCodigo", String.class);

        // Captura a exceção e verifica se é ArgumentoInvalidoException
        InvocationTargetException invocationTargetException = assertThrows(InvocationTargetException.class,
                () -> methodRemoverJogo.invoke(empresa, codigoInvalido),
                "Deveria lançar uma InvocationTargetException com ArgumentoInvalidoException como causa."
        );

        // Obtém a causa da InvocationTargetException
        Throwable causa = invocationTargetException.getCause();
        assertTrue(causa instanceof ArgumentoInvalidoException, "A causa deve ser ArgumentoInvalidoException.");

    }
}

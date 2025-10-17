package school.sptech.logica;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Empresa;
import school.sptech.Jogo;
import school.sptech.exception.JogoNaoEncontradoException;
import school.sptech.factory.EmpresaFactory;
import school.sptech.provider.BuscarMelhorAvaliacaoComSucessoProvider;
import school.sptech.provider.BuscarMelhorAvaliacaoEmpateProvider;
import school.sptech.provider.BuscarMelhorAvaliacaoSemJogosProvider;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static school.sptech.util.JogoUtil.verificarAtributoViaReflection;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("7. TESTE DE BUSCAR JOGO COM MELHOR AVALIAÇÃO - EMPRESA")
class EmpresaBuscarJogoComMelhorAvaliacaoTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarMelhorAvaliacaoComSucessoProvider.class)
    @DisplayName("7.1. Quando existem jogos, deve retornar o jogo com a melhor avaliação")
    void testBuscarMelhorAvaliacaoComSucesso(List<Jogo> jogos, Jogo jogoEsperado) throws Exception {
        // Usa a EmpresaFactory para criar uma instância de Empresa com jogos
        Empresa empresa = (Empresa) EmpresaFactory.build("Empresa Teste", jogos);

        // Usa reflection para chamar o método buscarJogoComMelhorAvaliacao
        Method methodBuscarMelhorAvaliacao = Empresa.class.getDeclaredMethod("buscarJogoComMelhorAvaliacao");
        Jogo jogoEncontrado = (Jogo) methodBuscarMelhorAvaliacao.invoke(empresa);

        // Valida os atributos do jogo encontrado via reflection
        verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "codigo", "O código do jogo deve ser o esperado.");
        verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "nome", "O nome do jogo deve ser o esperado.");
        verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "genero", "O gênero do jogo deve ser o esperado.");
        verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "preco", "O preço do jogo deve ser o esperado.");
        verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "avaliacao", "A avaliação do jogo deve ser a esperada.");
        verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "dataLancamento", "A data de lançamento do jogo deve ser a esperada.");
    }

    @ParameterizedTest
    @ArgumentsSource(BuscarMelhorAvaliacaoSemJogosProvider.class)
    @DisplayName("7.2. Quando não existem jogos, deve lançar JogoNaoEncontradoException")
    void testBuscarMelhorAvaliacaoSemJogos(List<Jogo> jogosVazios, String mensagemEsperada) throws Exception {
        // Usa a EmpresaFactory para criar uma instância de Empresa sem jogos
        Empresa empresa = (Empresa) EmpresaFactory.build("Empresa Teste", jogosVazios);

        // Usa reflection para chamar o método buscarJogoComMelhorAvaliacao
        Method methodBuscarMelhorAvaliacao = Empresa.class.getDeclaredMethod("buscarJogoComMelhorAvaliacao");

        // Captura a exceção e verifica se é JogoNaoEncontradoException
        InvocationTargetException invocationTargetException = assertThrows(InvocationTargetException.class,
                () -> methodBuscarMelhorAvaliacao.invoke(empresa),
                "Deveria lançar uma InvocationTargetException com JogoNaoEncontradoException como causa."
        );

        // Obtém a causa da InvocationTargetException
        Throwable causa = invocationTargetException.getCause();
        assertTrue(causa instanceof JogoNaoEncontradoException, "A causa deve ser JogoNaoEncontradoException.");
    }

    @ParameterizedTest
    @ArgumentsSource(BuscarMelhorAvaliacaoEmpateProvider.class)
    @DisplayName("7.3. Quando dois jogos têm a mesma avaliação, deve retornar o mais recente")
    void testBuscarMelhorAvaliacaoComEmpate(List<Jogo> jogos, Jogo jogoEsperado) throws Exception {
        // Usa a EmpresaFactory para criar uma instância de Empresa com jogos
        Empresa empresa = (Empresa) EmpresaFactory.build("Empresa Teste", jogos);

        // Usa reflection para chamar o método buscarJogoComMelhorAvaliacao
        Method methodBuscarMelhorAvaliacao = Empresa.class.getDeclaredMethod("buscarJogoComMelhorAvaliacao");
        Jogo jogoEncontrado = (Jogo) methodBuscarMelhorAvaliacao.invoke(empresa);

        // Valida os atributos do jogo encontrado via reflection
        verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "codigo", "O código do jogo deve ser o esperado.");
        verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "nome", "O nome do jogo deve ser o esperado.");
        verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "genero", "O gênero do jogo deve ser o esperado.");
        verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "preco", "O preço do jogo deve ser o esperado.");
        verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "avaliacao", "A avaliação do jogo deve ser a esperada.");
        verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "dataLancamento", "A data de lançamento do jogo deve ser a esperada.");
    }
}

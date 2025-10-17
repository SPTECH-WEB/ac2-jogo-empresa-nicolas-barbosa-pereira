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
import school.sptech.factory.JogoFactory;
import school.sptech.provider.BuscarJogoExistenteProvider;
import school.sptech.provider.BuscarJogoInvalidoProvider;
import school.sptech.provider.BuscarJogoNaoEncontradoProvider;
import school.sptech.util.JogoUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("5. TESTE DE BUSCAR JOGO POR CÓDIGO - EMPRESA")
public class EmpresaBuscarJogoPorCodigoTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarJogoExistenteProvider.class)
    @DisplayName("5.1. Quando o código é válido, deve retornar o jogo corretamente")
    void testBuscarJogoPorCodigoSucesso(String codigo, Jogo jogoEsperado) throws Exception {
        // Usa a EmpresaFactory para criar uma instância de Empresa com os jogos fornecidos pelo provider
        List<Jogo> jogos = new ArrayList<>();
        jogos.add(jogoEsperado);  // Reutiliza o objeto Jogo diretamente do provider

        // Cria a instância da Empresa com os jogos
        Empresa empresa = (Empresa) EmpresaFactory.build("Empresa Teste", jogos);

        // Usa reflection para chamar o método buscarJogoPorCodigo
        Method method = Empresa.class.getDeclaredMethod("buscarJogoPorCodigo", String.class);
        Jogo jogoEncontrado = (Jogo) method.invoke(empresa, codigo);

        // Valida os atributos do jogo encontrado via reflection
        JogoUtil.verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "codigo", "O código do jogo deve ser o esperado.");
        JogoUtil.verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "nome", "O nome do jogo deve ser o esperado.");
        JogoUtil.verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "genero", "O gênero do jogo deve ser o esperado.");
        JogoUtil.verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "preco", "O preço do jogo deve ser o esperado.");
        JogoUtil.verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "avaliacao", "A avaliação do jogo deve ser a esperada.");
        JogoUtil.verificarAtributoViaReflection(jogoEsperado, jogoEncontrado, "dataLancamento", "A data de lançamento do jogo deve ser a esperada.");
    }

    @ParameterizedTest
    @ArgumentsSource(BuscarJogoInvalidoProvider.class)
    @DisplayName("5.2. Quando o código é inválido, deve lançar ArgumentoInvalidoException")
    void testBuscarJogoPorCodigoInvalido(String codigoInvalido) throws Exception {
        // Usa a EmpresaFactory para criar uma instância de Empresa sem jogos
        Empresa empresa = (Empresa) EmpresaFactory.build("Empresa Teste", new ArrayList<>());

        // Usa reflection para chamar o método buscarJogoPorCodigo
        Method method = Empresa.class.getDeclaredMethod("buscarJogoPorCodigo", String.class);

        // Verifica se ArgumentoInvalidoException é lançada
        assertThrows(InvocationTargetException.class, () -> {
            try {
                method.invoke(empresa, codigoInvalido);
            } catch (InvocationTargetException e) {
                if (e.getCause() instanceof ArgumentoInvalidoException) {
                    throw e;
                } else {
                    throw new RuntimeException("Exceção inesperada.", e);
                }
            }
        }, "Deveria lançar ArgumentoInvalidoException para um código inválido.");
    }

    @ParameterizedTest
    @ArgumentsSource(BuscarJogoNaoEncontradoProvider.class)
    @DisplayName("5.3. Quando o jogo não for encontrado, deve lançar JogoNaoEncontradoException")
    void testBuscarJogoNaoEncontrado(String codigoInexistente) throws Exception {
        // Usa a EmpresaFactory para criar uma instância de Empresa com apenas 1 jogo
        List<Jogo> jogos = new ArrayList<>();
        jogos.add((Jogo) JogoFactory.build("GTA5", "Grand Theft Auto V", "Ação", 199.99, 4.8, LocalDate.of(2013, 9, 17)));

        Empresa empresa = (Empresa) EmpresaFactory.build("Empresa Teste", jogos);

        // Usa reflection para chamar o método buscarJogoPorCodigo
        Method method = Empresa.class.getDeclaredMethod("buscarJogoPorCodigo", String.class);

        // Verifica se JogoNaoEncontradoException é lançada
        assertThrows(InvocationTargetException.class, () -> {
            try {
                method.invoke(empresa, codigoInexistente);
            } catch (InvocationTargetException e) {
                if (e.getCause() instanceof JogoNaoEncontradoException) {
                    throw e;
                } else {
                    throw new RuntimeException("Exceção inesperada.", e);
                }
            }
        }, "Deveria lançar JogoNaoEncontradoException quando o jogo não for encontrado.");
    }
}

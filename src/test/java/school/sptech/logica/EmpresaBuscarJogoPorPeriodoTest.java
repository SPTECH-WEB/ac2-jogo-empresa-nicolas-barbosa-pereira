package school.sptech.logica;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import school.sptech.Empresa;
import school.sptech.Jogo;
import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.factory.EmpresaFactory;
import school.sptech.provider.BuscarJogoPorPeriodoInvalidoProvider;
import school.sptech.provider.BuscarJogoPorPeriodoValidoProvider;
import school.sptech.util.JogoUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("8. TESTE DE BUSCAR JOGO POR PERÍODO - EMPRESA")
class EmpresaBuscarJogoPorPeriodoTest {

    @ParameterizedTest
    @ArgumentsSource(BuscarJogoPorPeriodoValidoProvider.class)
    @DisplayName("8.1. Quando as datas são válidas, deve retornar os jogos dentro do período especificado")
    void testBuscarJogoPorPeriodoValido(LocalDate dataInicio, LocalDate dataFim, List<Jogo> jogos, List<Jogo> jogosEsperados) throws Exception {
        // Usa a EmpresaFactory para criar uma instância de Empresa com jogos
        Empresa empresa = (Empresa) EmpresaFactory.build("Empresa Teste", jogos);

        // Usa reflection para chamar o método buscarJogoPorPeriodo
        Method methodBuscarJogoPorPeriodo = Empresa.class.getDeclaredMethod("buscarJogoPorPeriodo", LocalDate.class, LocalDate.class);
        List<Jogo> jogosEncontrados = (List<Jogo>) methodBuscarJogoPorPeriodo.invoke(empresa, dataInicio, dataFim);

        // Verifica se os jogos retornados são os esperados
        assertEquals(jogosEsperados.size(), jogosEncontrados.size(), "O número de jogos encontrados deve ser o esperado.");

        for (int i = 0; i < jogosEsperados.size(); i++) {
            Jogo esperado = jogosEsperados.get(i);
            Jogo encontrado = jogosEncontrados.get(i);

            // Valida os atributos dos jogos encontrados via reflection
            JogoUtil.verificarAtributoViaReflection(esperado, encontrado, "codigo", "O código do jogo deve ser o esperado.");
            JogoUtil.verificarAtributoViaReflection(esperado, encontrado, "nome", "O nome do jogo deve ser o esperado.");
        }
    }

    @ParameterizedTest
    @ArgumentsSource(BuscarJogoPorPeriodoInvalidoProvider.class)
    @DisplayName("8.2. Quando as datas são inválidas, deve lançar ArgumentoInvalidoException")
    void testBuscarJogoPorPeriodoInvalido(LocalDate dataInicio, LocalDate dataFim, String mensagemEsperada) throws Exception {
        // Usa a EmpresaFactory para criar uma instância de Empresa
        Empresa empresa = (Empresa) EmpresaFactory.build("Empresa Teste", List.of());

        // Usa reflection para chamar o método buscarJogoPorPeriodo
        Method methodBuscarJogoPorPeriodo = Empresa.class.getDeclaredMethod("buscarJogoPorPeriodo", LocalDate.class, LocalDate.class);

        // Captura a exceção e verifica se é ArgumentoInvalidoException
        InvocationTargetException invocationTargetException = assertThrows(InvocationTargetException.class,
                () -> methodBuscarJogoPorPeriodo.invoke(empresa, dataInicio, dataFim),
                "Deveria lançar uma InvocationTargetException com ArgumentoInvalidoException como causa."
        );

        // Obtém a causa da InvocationTargetException
        Throwable causa = invocationTargetException.getCause();
        assertTrue(causa instanceof ArgumentoInvalidoException, "A causa deve ser ArgumentoInvalidoException.");
    }
}

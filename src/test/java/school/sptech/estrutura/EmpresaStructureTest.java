package school.sptech.estrutura;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import school.sptech.Empresa;
import school.sptech.Jogo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("2. TESTE DE ESTRUTURA - EMPRESA")
class EmpresaStructureTest {

    @Test
    @DisplayName("2.1. Deve possuir os atributos corretos com os modificadores apropriados")
    void testAtributos() throws NoSuchFieldException {
        // Verifica o atributo 'nome'
        Field nome = Empresa.class.getDeclaredField("nome");
        assertEquals(String.class, nome.getType(), "O tipo do atributo 'nome' deve ser String.");
        assertTrue(Modifier.isPrivate(nome.getModifiers()), "O atributo 'nome' deve ser privado.");

        // Verifica o atributo 'jogos'
        Field jogos = Empresa.class.getDeclaredField("jogos");
        assertEquals(List.class, jogos.getType(), "O tipo do atributo 'jogos' deve ser List.");
        assertTrue(Modifier.isPrivate(jogos.getModifiers()), "O atributo 'jogos' deve ser privado.");
    }

    @Test
    @DisplayName("2.2. Deve possuir os construtores corretos")
    void testConstrutores() throws NoSuchMethodException, NoSuchFieldException, IllegalAccessException {
        // Verifica o construtor sem argumentos
        Constructor<Empresa> construtorPadrao = Empresa.class.getDeclaredConstructor();
        assertTrue(Modifier.isPublic(construtorPadrao.getModifiers()), "O construtor sem argumentos deve ser público.");

        Empresa empresa = new Empresa();
        Field jogos = Empresa.class.getDeclaredField("jogos");
        jogos.setAccessible(true);
        List<Jogo> jogosDaEmpresa = (List<Jogo>) jogos.get(empresa);
        assertNotNull(jogosDaEmpresa, "O atributo 'jogos' deve ser inicializado no construtor.");

    }

    @Test
    @DisplayName("2.3. Deve possuir os métodos getters e setters corretos com modificadores públicos")
    void testGettersSetters() throws NoSuchMethodException {
        // Verifica o getter e setter de 'nome'
        Method getNome = Empresa.class.getDeclaredMethod("getNome");
        assertEquals(String.class, getNome.getReturnType(), "O método 'getNome' deve retornar String.");
        assertTrue(Modifier.isPublic(getNome.getModifiers()), "O método 'getNome' deve ser público.");

        Method setNome = Empresa.class.getDeclaredMethod("setNome", String.class);
        assertTrue(Modifier.isPublic(setNome.getModifiers()), "O método 'setNome' deve ser público.");

        // Verifica o getter de 'jogos'
        Method getJogos = Empresa.class.getDeclaredMethod("getJogos");
        assertEquals(List.class, getJogos.getReturnType(), "O método 'getJogos' deve retornar List.");
        assertTrue(Modifier.isPublic(getJogos.getModifiers()), "O método 'getJogos' deve ser público.");
    }

    @Test
    @DisplayName("2.4. Deve possuir o método adicionarJogo com modificador público")
    void testAdicionarJogo() throws NoSuchMethodException {
        Method adicionarJogo = Empresa.class.getDeclaredMethod("adicionarJogo", Jogo.class);
        assertTrue(Modifier.isPublic(adicionarJogo.getModifiers()), "O método 'adicionarJogo' deve ser público.");
    }

    @Test
    @DisplayName("2.5. Deve possuir o método buscarJogoPorCodigo com modificador público")
    void testBuscarJogoPorCodigo() throws NoSuchMethodException {
        Method buscarJogoPorCodigo = Empresa.class.getDeclaredMethod("buscarJogoPorCodigo", String.class);
        assertEquals(Jogo.class, buscarJogoPorCodigo.getReturnType(), "O método 'buscarJogoPorCodigo' deve retornar Jogo.");
        assertTrue(Modifier.isPublic(buscarJogoPorCodigo.getModifiers()), "O método 'buscarJogoPorCodigo' deve ser público.");
    }

    @Test
    @DisplayName("2.6. Deve possuir o método removerJogoPorCodigo com modificador público")
    void testRemoverJogoPorCodigo() throws NoSuchMethodException {
        Method removerJogoPorCodigo = Empresa.class.getDeclaredMethod("removerJogoPorCodigo", String.class);
        assertTrue(Modifier.isPublic(removerJogoPorCodigo.getModifiers()), "O método 'removerJogoPorCodigo' deve ser público.");
    }

    @Test
    @DisplayName("2.7. Deve possuir o método buscarJogoComMelhorAvaliacao com modificador público")
    void testBuscarJogoComMelhorAvaliacao() throws NoSuchMethodException {
        Method buscarJogoComMelhorAvaliacao = Empresa.class.getDeclaredMethod("buscarJogoComMelhorAvaliacao");
        assertEquals(Jogo.class, buscarJogoComMelhorAvaliacao.getReturnType(), "O método 'buscarJogoComMelhorAvaliacao' deve retornar Jogo.");
        assertTrue(Modifier.isPublic(buscarJogoComMelhorAvaliacao.getModifiers()), "O método 'buscarJogoComMelhorAvaliacao' deve ser público.");
    }

    @Test
    @DisplayName("2.8. Deve possuir o método buscarJogoPorPeriodo com modificador público")
    void testBuscarJogoPorPeriodo() throws NoSuchMethodException {
        Method buscarJogoPorPeriodo = Empresa.class.getDeclaredMethod("buscarJogoPorPeriodo", LocalDate.class, LocalDate.class);
        assertEquals(List.class, buscarJogoPorPeriodo.getReturnType(), "O método 'buscarJogoPorPeriodo' deve retornar List.");
        assertTrue(Modifier.isPublic(buscarJogoPorPeriodo.getModifiers()), "O método 'buscarJogoPorPeriodo' deve ser público.");
    }

    @Test
    @DisplayName("2.9. Deve garantir que não há setter para o atributo 'jogos'")
    void testNaoExisteSetterParaJogos() {
        Method[] methods = Empresa.class.getDeclaredMethods();

        boolean hasSetterForJogos = false;
        for (Method method : methods) {
            if (method.getName().equals("setJogos")) {
                hasSetterForJogos = true;
                break;
            }
        }

        assertFalse(hasSetterForJogos, "A classe 'Empresa' não deve possuir um setter para a lista 'jogos'.");
    }
}
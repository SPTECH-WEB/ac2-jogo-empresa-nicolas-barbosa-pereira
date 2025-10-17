package school.sptech.estrutura;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import school.sptech.Jogo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("1. TESTE DE ESTRUTURA - JOGO")
class JogoStructureTest {

    @Test
    @DisplayName("1.1. Deve possuir os atributos corretos com os modificadores apropriados")
    void testAtributos() throws NoSuchFieldException {

        Field codigo = Jogo.class.getDeclaredField("codigo");
        assertEquals(String.class, codigo.getType(), "O tipo do atributo 'codigo' deve ser String.");
        assertTrue(Modifier.isPrivate(codigo.getModifiers()), "O atributo 'codigo' deve ser privado.");

        Field nome = Jogo.class.getDeclaredField("nome");
        assertEquals(String.class, nome.getType(), "O tipo do atributo 'nome' deve ser String.");
        assertTrue(Modifier.isPrivate(nome.getModifiers()), "O atributo 'nome' deve ser privado.");

        Field genero = Jogo.class.getDeclaredField("genero");
        assertEquals(String.class, genero.getType(), "O tipo do atributo 'genero' deve ser String.");
        assertTrue(Modifier.isPrivate(genero.getModifiers()), "O atributo 'genero' deve ser privado.");

        Field preco = Jogo.class.getDeclaredField("preco");
        assertEquals(Double.class, preco.getType(), "O tipo do atributo 'preco' deve ser Double.");
        assertTrue(Modifier.isPrivate(preco.getModifiers()), "O atributo 'preco' deve ser privado.");

        Field avaliacao = Jogo.class.getDeclaredField("avaliacao");
        assertEquals(Double.class, avaliacao.getType(), "O tipo do atributo 'avaliacao' deve ser Double.");
        assertTrue(Modifier.isPrivate(avaliacao.getModifiers()), "O atributo 'avaliacao' deve ser privado.");

        Field dataLancamento = Jogo.class.getDeclaredField("dataLancamento");
        assertEquals(LocalDate.class, dataLancamento.getType(), "O tipo do atributo 'dataLancamento' deve ser LocalDate.");
        assertTrue(Modifier.isPrivate(dataLancamento.getModifiers()), "O atributo 'dataLancamento' deve ser privado.");
    }

    @Test
    @DisplayName("1.2. Deve possuir os construtores corretos")
    void testConstrutores() throws NoSuchMethodException {
        Constructor<Jogo> construtorPadrao = Jogo.class.getDeclaredConstructor();
        assertTrue(Modifier.isPublic(construtorPadrao.getModifiers()), "O construtor sem argumentos deve ser público.");
    }

    @Test
    @DisplayName("1.3. Deve possuir os métodos getters e setters corretos com modificadores públicos")
    void testGettersSetters() throws NoSuchMethodException {
        Method getCodigo = Jogo.class.getDeclaredMethod("getCodigo");
        assertEquals(String.class, getCodigo.getReturnType(), "O método 'getCodigo' deve retornar String.");
        assertTrue(Modifier.isPublic(getCodigo.getModifiers()), "O método 'getCodigo' deve ser público.");

        Method setCodigo = Jogo.class.getDeclaredMethod("setCodigo", String.class);
        assertTrue(Modifier.isPublic(setCodigo.getModifiers()), "O método 'setCodigo' deve ser público.");

        Method getNome = Jogo.class.getDeclaredMethod("getNome");
        assertEquals(String.class, getNome.getReturnType(), "O método 'getNome' deve retornar String.");
        assertTrue(Modifier.isPublic(getNome.getModifiers()), "O método 'getNome' deve ser público.");

        Method setNome = Jogo.class.getDeclaredMethod("setNome", String.class);
        assertTrue(Modifier.isPublic(setNome.getModifiers()), "O método 'setNome' deve ser público.");

        Method getGenero = Jogo.class.getDeclaredMethod("getGenero");
        assertEquals(String.class, getGenero.getReturnType(), "O método 'getGenero' deve retornar String.");
        assertTrue(Modifier.isPublic(getGenero.getModifiers()), "O método 'getGenero' deve ser público.");

        Method setGenero = Jogo.class.getDeclaredMethod("setGenero", String.class);
        assertTrue(Modifier.isPublic(setGenero.getModifiers()), "O método 'setGenero' deve ser público.");

        Method getPreco = Jogo.class.getDeclaredMethod("getPreco");
        assertEquals(Double.class, getPreco.getReturnType(), "O método 'getPreco' deve retornar Double.");
        assertTrue(Modifier.isPublic(getPreco.getModifiers()), "O método 'getPreco' deve ser público.");

        Method setPreco = Jogo.class.getDeclaredMethod("setPreco", Double.class);
        assertTrue(Modifier.isPublic(setPreco.getModifiers()), "O método 'setPreco' deve ser público.");

        Method getAvaliacao = Jogo.class.getDeclaredMethod("getAvaliacao");
        assertEquals(Double.class, getAvaliacao.getReturnType(), "O método 'getAvaliacao' deve retornar Double.");
        assertTrue(Modifier.isPublic(getAvaliacao.getModifiers()), "O método 'getAvaliacao' deve ser público.");

        Method setAvaliacao = Jogo.class.getDeclaredMethod("setAvaliacao", Double.class);
        assertTrue(Modifier.isPublic(setAvaliacao.getModifiers()), "O método 'setAvaliacao' deve ser público.");

        Method getDataLancamento = Jogo.class.getDeclaredMethod("getDataLancamento");
        assertEquals(LocalDate.class, getDataLancamento.getReturnType(), "O método 'getDataLancamento' deve retornar LocalDate.");
        assertTrue(Modifier.isPublic(getDataLancamento.getModifiers()), "O método 'getDataLancamento' deve ser público.");

        Method setDataLancamento = Jogo.class.getDeclaredMethod("setDataLancamento", LocalDate.class);
        assertTrue(Modifier.isPublic(setDataLancamento.getModifiers()), "O método 'setDataLancamento' deve ser público.");
    }

    @Test
    @DisplayName("1.4. Deve possuir o método getAvaliacaoDescricao com modificador público")
    void testMetodoGetAvaliacaoDescricao() throws NoSuchMethodException {
        Method getAvaliacaoDescricao = Jogo.class.getDeclaredMethod("getAvaliacaoDescricao");
        assertEquals(String.class, getAvaliacaoDescricao.getReturnType(), "O método 'getAvaliacaoDescricao' deve retornar String.");
        assertTrue(Modifier.isPublic(getAvaliacaoDescricao.getModifiers()), "O método 'getAvaliacaoDescricao' deve ser público.");
    }
}
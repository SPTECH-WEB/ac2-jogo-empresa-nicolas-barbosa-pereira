package school.sptech.util;

import school.sptech.Jogo;

import java.lang.reflect.Field;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JogoUtil {

    public static void verificarAtributoViaReflection(Jogo esperado, Jogo encontrado, String nomeCampo, String mensagem) throws Exception {
        Field campoEsperado = esperado.getClass().getDeclaredField(nomeCampo);
        campoEsperado.setAccessible(true);

        Field campoEncontrado = encontrado.getClass().getDeclaredField(nomeCampo);
        campoEncontrado.setAccessible(true);

        Object valorEsperado = campoEsperado.get(esperado);
        Object valorEncontrado = campoEncontrado.get(encontrado);

        assertEquals(valorEsperado, valorEncontrado, mensagem);
    }

    public static Jogo configurarJogo(String codigo, String nome, String genero, double preco, double avaliacao, LocalDate dataLancamento) {
        try {
            // Cria uma nova instância de Jogo usando o construtor default
            Jogo jogo = Jogo.class.getDeclaredConstructor().newInstance();

            // Define os campos individualmente usando reflection
            configurarCampo(jogo, "codigo", codigo);
            configurarCampo(jogo, "nome", nome);
            configurarCampo(jogo, "genero", genero);
            configurarCampo(jogo, "preco", preco);
            configurarCampo(jogo, "avaliacao", avaliacao);
            configurarCampo(jogo, "dataLancamento", dataLancamento);

            return jogo;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao configurar o objeto Jogo", e);
        }
    }

    public static void configurarCampo(Object objeto, String nomeCampo, Object valor) throws Exception {
        Field campo = objeto.getClass().getDeclaredField(nomeCampo);
        campo.setAccessible(true); // Permite o acesso a campos privados
        campo.set(objeto, valor);  // Define o valor do campo
    }
}

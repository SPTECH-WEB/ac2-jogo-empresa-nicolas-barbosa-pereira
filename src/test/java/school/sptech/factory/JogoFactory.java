package school.sptech.factory;

import school.sptech.Jogo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class JogoFactory {

    static Map<String, Field> campos() throws ReflectiveOperationException {
        Class<Jogo> clazz = Jogo.class;

        Map<String, Field> mapCampos = new HashMap<>();
        String[] nomeCampos = {"codigo", "nome", "genero", "preco", "avaliacao", "dataLancamento"};

        for (String campoNome : nomeCampos) {
            Field campo = clazz.getDeclaredField(campoNome);
            campo.trySetAccessible();

            mapCampos.put(campoNome, campo);
        }

        return mapCampos;
    }

    public static Object build(String codigo, String nome, String genero, Double preco, Double avaliacao, LocalDate dataLancamento) throws ReflectiveOperationException {
        Class<Jogo> jogoClass = Jogo.class;

        Constructor<?> constructor = jogoClass.getDeclaredConstructors()[0];
        Object[] parameters = new Object[constructor.getParameterCount()];

        Object obj = constructor.newInstance(parameters);

        campos().get("codigo").set(obj, codigo);
        campos().get("nome").set(obj, nome);
        campos().get("genero").set(obj, genero);
        campos().get("preco").set(obj, preco);
        campos().get("avaliacao").set(obj, avaliacao);
        campos().get("dataLancamento").set(obj, dataLancamento);

        return obj;
    }
}

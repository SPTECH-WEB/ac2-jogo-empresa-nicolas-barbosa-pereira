package school.sptech.factory;

import school.sptech.Empresa;
import school.sptech.Jogo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmpresaFactory {

    static Map<String, Field> campos() throws ReflectiveOperationException {
        Class<Empresa> clazz = Empresa.class;

        Map<String, Field> mapCampos = new HashMap<>();
        String[] nomeCampos = {"nome", "jogos"};

        for (String campoNome : nomeCampos) {
            Field campo = clazz.getDeclaredField(campoNome);
            campo.trySetAccessible();

            mapCampos.put(campoNome, campo);
        }

        return mapCampos;
    }

    public static Object build(String nome, List<Jogo> jogos) throws ReflectiveOperationException {
        Class<Empresa> empresaClass = Empresa.class;

        Constructor<?> constructor = empresaClass.getDeclaredConstructors()[0];
        Object[] parameters = Arrays.stream(constructor.getParameters()).map(param -> null).toArray();

        Object obj = constructor.newInstance(parameters);

        campos().get("nome").set(obj, nome);
        campos().get("jogos").set(obj, jogos);

        return obj;
    }
}

package lotto;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class SingletonContainer {

    private static final Map<Class<?>, Object> instances = new HashMap<>();

    public static <T> T getInstance(Class<T> clazz) {
        synchronized (instances) {
            if (!instances.containsKey(clazz)) {
                try {
                    return createInstance(clazz);
                } catch (Exception e) {
                    throw new RuntimeException("[ERROR] 인스턴스 생성에 실패했습니다");
                }
            }
            return clazz.cast(instances.get(clazz));
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> T createInstance(Class<T> clazz)
            throws InstantiationException, IllegalAccessException, InvocationTargetException {
        Constructor<?> targetConstructor = getConstructor(clazz);
        Object[] parameters = getParameters(targetConstructor);
        targetConstructor.setAccessible(true);
        T instance = (T) targetConstructor.newInstance(parameters);

        instances.put(clazz, instance);
        return instance;
    }

    private static <T> Constructor<?> getConstructor(Class<T> clazz) {
        Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();
        Constructor<?> targetConstructor = declaredConstructors[0];
        for (Constructor<?> constructor : declaredConstructors) {
            int parameterCount = constructor.getParameterCount();
            if (parameterCount > targetConstructor.getParameterCount()) {
                targetConstructor = constructor;
            }
        }
        return targetConstructor;
    }

    private static Object[] getParameters(Constructor<?> targetConstructor) {
        Class<?>[] parameterTypes = targetConstructor.getParameterTypes();
        Object[] parameters = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            if (instances.containsKey(parameterType)) continue;
            Object parameter = getInstance(parameterType);
            parameters[i] = parameter;
        }
        return parameters;
    }

    public static boolean contains(Class<?> clazz) {
        return instances.containsKey(clazz);
    }
}

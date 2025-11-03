package lotto.singleton;

import java.io.File;
import java.net.URL;
import java.util.Objects;

public class SingletonScanner {

    public static void scan(String basePackage) {
        String path = basePackage.replace('.', '/');
        URL resource = Thread.currentThread().getContextClassLoader().getResource(path);
        if (resource == null) return;

        File directory = new File(resource.getFile());
        for (File file : Objects.requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                scan(basePackage + "." + file.getName());
            }
            if (file.toPath().endsWith(".class")) {
                try {
                    String className = basePackage + "." + file.getName().replace(".class", "");
                    Class<?> clazz = Class.forName(className);

                    if (clazz.isAnnotationPresent(Singleton.class) && !SingletonContainer.contains(clazz)) {
                        SingletonContainer.getInstance(clazz);
                        System.out.println("[등록 완료] " + clazz.getName());
                    }
                } catch (Exception e) {
                    throw new RuntimeException("싱글톤 스캔 중 오류 발생: " + e.getMessage(), e);
                }
            }
        }
    }
}

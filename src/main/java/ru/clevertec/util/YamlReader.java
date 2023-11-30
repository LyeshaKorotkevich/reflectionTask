package ru.clevertec.util;

import org.yaml.snakeyaml.Yaml;
import ru.clevertec.Main;

import java.io.InputStream;
import java.util.Map;

/**
 * Класс для чтения конфигурационных параметров из YAML файла.
 */
public class YamlReader {
    private static final String CONFIG_FILE = "application.yml";

    private static final Map<String, Object> configMap;

    static {
        Yaml yaml = new Yaml();
        try (InputStream inputStream = YamlReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream != null) {
                configMap = yaml.load(inputStream);
            } else {
                throw new RuntimeException("Could not load " + CONFIG_FILE);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error loading " + CONFIG_FILE, e);
        }
    }

    /**
     * Получает значение размера кэша из конфигурационного файла.
     *
     * @return размер кэша.
     * @throws RuntimeException Если значение не найдено в конфигурационном файле.
     */
    public static Integer getMaxSize() {
        if (configMap != null && configMap.containsKey("maxSize")) {
            return (Integer) configMap.get("maxSize");
        } else {
            throw new RuntimeException("Maximum size is not found in config");
        }
    }

    /**
     * Получает вид алгоритма кэширования из конфигурационного файла.
     *
     * @return алгоритм.
     * @throws RuntimeException Если значение не найдено в конфигурационном файле.
     */
    public static String getAlgorithm() {
        if (configMap != null && configMap.containsKey("algorithm")) {
            return (String) configMap.get("algorithm");
        } else {
            throw new RuntimeException("Algorithm is not found in config");
        }
    }
}
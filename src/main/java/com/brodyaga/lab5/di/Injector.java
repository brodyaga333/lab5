package com.brodyaga.lab5.di;

import com.brodyaga.lab5.annotations.AutoInjectable;

import java.io.InputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;


/**
 * Класс-инжектор, который внедряет зависимости в поля объектов,
 * помеченные аннотацией @AutoInjectable, используя конфигурацию из properties-файла.
 */
public class Injector {
    private final Properties properties = new Properties();


    /**
     * Загружает конфигурацию из файла config.properties в classpath.
     */
    public Injector() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input != null) {
                properties.load(input);
            } else {
                throw new RuntimeException("config.properties не найден");
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке config.properties", e);
        }
    }


    /**
     * Выполняет внедрение зависимостей в поля объекта, помеченные @AutoInjectable.
     * @param obj объект, в поля которого нужно внедрить зависимости
     * @param <T> тип объекта
     * @return тот же объект с внедрёнными зависимостями
     */
    public <T> T inject(T obj) {
        Class<?> clazz = obj.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                String implClassName = properties.getProperty(field.getType().getCanonicalName());
                if (implClassName != null) {
                    try {
                        Class<?> implClass = Class.forName(implClassName);
                        Object instance = implClass.getDeclaredConstructor().newInstance();
                        field.setAccessible(true);
                        field.set(obj, instance);
                    } catch (Exception e) {
                        throw new RuntimeException("Не удалось внедрить зависимость в поле: " + field.getName(), e);
                    }
                }
            }
        }
        return obj;
    }
}

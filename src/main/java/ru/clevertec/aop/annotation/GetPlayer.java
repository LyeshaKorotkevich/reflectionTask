package ru.clevertec.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Ищем в кеше и если там данных нет, то достаем объект из dao, сохраняем в кеш и возвращаем
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface GetPlayer {
}

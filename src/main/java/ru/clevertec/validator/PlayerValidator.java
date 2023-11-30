package ru.clevertec.validator;

import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import ru.clevertec.validator.annotation.TShirtNumber;

import java.lang.reflect.Field;
import java.time.LocalDate;

/**
 * Класс, отвечающий за валидацию полей объекта с использованием аннотаций.
 */
public class PlayerValidator {

    /**
     * Проверяет объект на соответствие установленным аннотациям валидации.
     *
     * @param object Объект, подлежащий валидации.
     * @return true, если объект прошел валидацию, иначе ValidationException.
     * @throws ValidationException    выбрасывается, если объект не прошел валидацию.
     * @throws IllegalAccessException выбрасывается, если нет доступа к полям объекта.
     */
    public static boolean validate(Object object) throws ValidationException, IllegalAccessException {
        Class<?> objectClass = object.getClass();
        for (Field field : objectClass.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(TShirtNumber.class)) {
                validateNumber(field, object);
            }

            if (field.isAnnotationPresent(NotBlank.class)) {
                validateNotBlank(field, object);
            }

            if (field.isAnnotationPresent(NotNull.class)) {
                validateNotNull(field, object);
            }

            if (field.isAnnotationPresent(Past.class)) {
                validatePast(field, object);
            }

            if (field.isAnnotationPresent(Pattern.class)) {
                validatePattern(field, object);
            }
        }
        return true;
    }

    private static void validateNumber(Field field, Object object) throws IllegalAccessException {
        TShirtNumber numberAnnotation = field.getAnnotation(TShirtNumber.class);
        int number = field.getInt(object);

        int min = numberAnnotation.min();
        int max = numberAnnotation.max();

        if (number < min || number > max) {
            throw new ValidationException();
        }
    }

    private static void validateNotBlank(Field field, Object object) throws IllegalAccessException, ValidationException {
        if (field.get(object) == null || ((String) field.get(object)).trim().isEmpty()) {
            throw new ValidationException("Field " + field.getName() + " must not be blank");
        }
    }

    private static void validateNotNull(Field field, Object object) throws IllegalAccessException, ValidationException {
        if (field.get(object) == null) {
            throw new ValidationException("Field " + field.getName() + " must not be null");
        }
    }

    private static void validatePast(Field field, Object object) throws IllegalAccessException, ValidationException {
        if (field.getType() == LocalDate.class) {
            LocalDate date = (LocalDate) field.get(object);
            if (date != null && date.isAfter(LocalDate.now())) {
                throw new ValidationException("Field " + field.getName() + " must be in the past");
            }
        }
    }

    private static void validatePattern(Field field, Object object) throws IllegalAccessException, ValidationException {
        if (field.isAnnotationPresent(Pattern.class)) {
            Pattern patternAnnotation = field.getAnnotation(Pattern.class);
            String fieldValue = (String) field.get(object);

            if (fieldValue != null && !fieldValue.matches(patternAnnotation.regexp())) {
                throw new ValidationException("Field " + field.getName() + " does not match the pattern");
            }
        }
    }
}

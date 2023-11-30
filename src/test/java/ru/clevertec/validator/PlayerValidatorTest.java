package ru.clevertec.validator;

import jakarta.validation.ValidationException;
import org.junit.jupiter.api.Test;
import ru.clevertec.dto.PlayerDto;
import util.PlayerTestData;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerValidatorTest {

    @Test
    public void validate_ValidPlayerDto_ReturnsTrue() {
        // given
        PlayerDto playerDto = PlayerTestData.builder().build().buildPlayerDto();

        // when, then
        assertDoesNotThrow(() -> PlayerValidator.validate(playerDto));
    }

    @Test
    public void validate_InvalidName_ThrowsValidationException() {
        // given
        PlayerDto playerDto1 = PlayerTestData.builder().withName("alex1").build().buildPlayerDto();
        PlayerDto playerDto2 = PlayerTestData.builder().withName("").build().buildPlayerDto();

        // when, then
        assertThrows(ValidationException.class, () -> PlayerValidator.validate(playerDto1));
        assertThrows(ValidationException.class, () -> PlayerValidator.validate(playerDto2));
    }

    @Test
    public void validate_InvalidSurname_ThrowsValidationException() {
        // given
        PlayerDto playerDto1 = PlayerTestData.builder().withSurname("alex32").build().buildPlayerDto();
        PlayerDto playerDto2 = PlayerTestData.builder().withSurname("").build().buildPlayerDto();

        // when, then
        assertThrows(ValidationException.class, () -> PlayerValidator.validate(playerDto1));
        assertThrows(ValidationException.class, () -> PlayerValidator.validate(playerDto2));
    }

    @Test
    public void validate_NullDateOfBirth_ThrowsValidationException() {
        // given
        PlayerDto playerDto = PlayerTestData.builder().withDateBirth(null).build().buildPlayerDto();

        // when, then
        assertThrows(ValidationException.class, () -> PlayerValidator.validate(playerDto));
    }

    @Test
    public void validate_DateOfBirthInFuture_ThrowsValidationException() {
        // given
        PlayerDto playerDto = PlayerTestData.builder().withDateBirth(LocalDate.now().plusDays(1)).build().buildPlayerDto();

        // when, then
        assertThrows(ValidationException.class, () -> PlayerValidator.validate(playerDto));
    }

    @Test
    public void validate_InvalidTShirtNumber_ThrowsValidationException() {
        // given
        PlayerDto playerDto = PlayerTestData.builder().withNumber(100).build().buildPlayerDto();

        // when, then
        assertThrows(ValidationException.class, () -> PlayerValidator.validate(playerDto));
    }
}
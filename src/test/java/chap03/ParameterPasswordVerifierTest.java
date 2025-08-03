package chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ParameterPasswordVerifierTest {
    private final ParameterPasswordVerifier verifier = new ParameterPasswordVerifier();

    @Test
    @DisplayName("평일에는 예외를 던지지 않아야 한다.")
    void verifyOnWeekdayDoesNotThrowException(){
        // Given
        DayOfWeek aMonday = LocalDate.of(2025, 8,4).getDayOfWeek();

        // When & Then
        assertDoesNotThrow(() -> verifier.verifyPassword("anything", aMonday));
    }

    @Test
    @DisplayName("주말에는 예외를 던져야 한다.")
    void verifyOnWeekendThrowException(){
        DayOfWeek aSaturday = DayOfWeek.SATURDAY;

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> verifier.verifyPassword("anything", aSaturday));

        assertThat(exception.getMessage()).isEqualTo("It's the weekend!");
    }
}
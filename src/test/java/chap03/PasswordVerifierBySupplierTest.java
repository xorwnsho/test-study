package chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.function.Supplier;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PasswordVerifierBySupplierTest {

    private final PasswordVerifierBySupplier verifier = new PasswordVerifierBySupplier();

    @Test
    @DisplayName("월요일 스텁으로 검증 시 예외를 던지지 않아야 한다.")
    void verifyWithMondayStubDoesNotThrowException(){

        // Given
        Supplier<DayOfWeek> alwaysMonday = () -> DayOfWeek.MONDAY;

        // When & Then
        assertDoesNotThrow(()-> verifier.verifyPassword("anything", alwaysMonday));
    }

    @Test
    @DisplayName("토요일 스텁으로 검증 시 예외를 던져야 한다")
    void verifyWithSaturdayStubThrowsException(){

        // Given
        Supplier<DayOfWeek> alwaysSaturday = () -> DayOfWeek.SATURDAY;

        // When & Then
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> verifier.verifyPassword("anything", alwaysSaturday));

        assertThat(exception.getMessage()).isEqualTo("It's the weekend!");
    }


}
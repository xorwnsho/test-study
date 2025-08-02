package chap03;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UntestablePasswordVerifierTest {
    private final UntestablePasswordVerifier verifier = new UntestablePasswordVerifier();

    @Test
    @DisplayName("주말에는 예외를 던져야 한다.")
    void verifyOnWeekendsThrowsException(){
        DayOfWeek day = LocalDate.now().getDayOfWeek();

        if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY){
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> verifier.verifyPassword("any value"));

            assertThat(exception.getMessage()).isEqualTo("It's the weekend!");
        }
    }
}
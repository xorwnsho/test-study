package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordVerifierErrorTest {

    @Test
    @DisplayName("verify password error scenarios")
    void throwsExceptionWhenNoRulesAreCounfigured(){
        PasswordVerifierError verifier = new PasswordVerifierError();

        try{
            verifier.verifyPassword("any value");
        } catch (IllegalStateException e){
            assertTrue(e.getMessage().contains("no rules configured"));
        }
    }

    @Test
    @DisplayName("Throws IllegalStateException when no rules are configured")
    void shouldContainCorrectErrorMessage(){
        PasswordVerifierError verifier = new PasswordVerifierError();

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> verifier.verifyPassword("any value"));
        assertTrue(exception.getMessage().contains("no rules configured"));
    }

}
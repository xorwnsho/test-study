package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


class PasswordVerifierTestV1 {
    PasswordValidationRule failingRule = input -> new ValidationResult(false, "fake reason");
    PasswordValidationRule passingRule = input -> new ValidationResult(true, "");

    @Test
    @DisplayName("has an error message based on the rule's reason")
    void hasErrorMessageBaseOnRuleReason(){

        // Given
        PasswordVerifier verifier = new PasswordVerifier();
        verifier.addRule(failingRule);

        // when
        List<String> errors = verifier.verifyPassword("any value");

        // then
        assertThat(errors).hasSize(1);
        assertThat(errors.getFirst().contains("fake reason"));

    }
}
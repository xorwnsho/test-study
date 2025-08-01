package chap02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("PasswordVerifier Test")
class PasswordVerifierTestV4HelperMethod {

    PasswordValidationRule failingRule = input -> new ValidationResult(false, "fake reason");
    PasswordValidationRule passingRule = input -> new ValidationResult(true, "");
    PasswordValidationRule anotherFailingRule = input -> new ValidationResult(false, "another fake reason");

    @Nested
    @DisplayName("when a rule fails")
    class FailingRuleScenario{

        @Test
        @DisplayName("has an error message based on the rule's reason")
        void hasErrorMessageBaseOnRuleReason(){
            PasswordVerifier verifier = makeVerifierWithFailingRule();
            List<String> errors = verifier.verifyPassword("any value");

            assertThat(errors).hasSize(1);
            assertThat(errors.getFirst().contains("fake reason"));
        }

        @Test
        @DisplayName("has exactly one error")
        void hasExactlyOneError(){
            PasswordVerifier verifier = makeVerifierWithFailingRule();

            List<String> errors = verifier.verifyPassword("any value");

            assertThat(errors).hasSize(1);
        }

        private PasswordVerifier makeVerifier() {
            return new PasswordVerifier();
        }

        private PasswordVerifier makeVerifierWithFailingRule() {
            var verifier = makeVerifier();
            verifier.addRule(failingRule);
            return verifier;
        }
    }
}
package chap02;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("PasswordVerifier Test")
class PasswordVerifierTestV2PlusNested {

    PasswordValidationRule failingRule = input -> new ValidationResult(false, "fake reason");
    PasswordValidationRule passingRule = input -> new ValidationResult(true, "");
    PasswordValidationRule anotherFailingRule = input -> new ValidationResult(false, "another fake reason");

    @Nested
    @DisplayName("when a rule fails")
    class FailingRuleScenario{

        @Test
        @DisplayName("has an error message based on the rule's reason")
        void hasErrorMessageBaseOnRuleReason(){
            PasswordVerifier verifier = new PasswordVerifier();
            verifier.addRule(failingRule);

            List<String> errors = verifier.verifyPassword("any value");

//            assertThat(errors).hasSize(1);
            assertThat(errors.getFirst().contains("fake reason"));
        }

        @Test
        @DisplayName("has exactly one error")
        void hasExactlyOneError(){
            PasswordVerifier verifier = new PasswordVerifier();
            verifier.addRule(failingRule);

            List<String> errors = verifier.verifyPassword("any value");

            assertThat(errors).hasSize(1);
        }
    }

}

// 테스트 케이스를 분리하면 PasswordVerifier 객체를 생성하고 addRule() 을 호출하는 중복 코드가 발생
// 이 문제는 JUnit의 @BeforeEach와 같은 초기화 메서드를 사용하면 해결 가능
// @BeforeEach는 각 @Test 메서드가 실행되기 전에 자동으로 호출되므로, 중복 코드를 제거하고 테스트 코트를 더 간결하고 효율적으로 만든다
// 이처럼 상태 기반 코드를 테스트할 때는 작업 단위의 범위를 고려하고, 검증 룰렛을 피하기 위해 테스트를 분리하며,
// 중복 코드를 줄이기 위해 초기화 메서드를 활용해야 한다.
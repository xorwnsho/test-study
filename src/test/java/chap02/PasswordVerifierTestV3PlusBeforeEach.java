package chap02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DisplayName("PasswordVerifier Test")
class PasswordVerifierTestV3PlusBeforeEach {

    PasswordValidationRule failingRule = input -> new ValidationResult(false, "fake reason");
    PasswordValidationRule passingRule = input -> new ValidationResult(true, "");
    PasswordValidationRule anotherFailingRule = input -> new ValidationResult(false, "another fake reason");

    PasswordVerifier verifier;

    @BeforeEach
    void setUp(){
        verifier = new PasswordVerifier();
    }

    @Nested
    @DisplayName("when a rule fails")
    class FailingRuleScenario{

        @BeforeEach
        void setUp(){
            verifier.addRule(failingRule);
        }

        @Test
        @DisplayName("has an error message based on the rule's reason")
        void hasErrorMessageBaseOnRuleReason(){
            List<String> errors = verifier.verifyPassword("any value");

            assertThat(errors).hasSize(1);
            assertThat(errors.getFirst().contains("fake reason"));
        }

        @Test
        @DisplayName("has exactly one error")
        void hasExactlyOneError(){
            List<String> errors = verifier.verifyPassword("any value");

            assertThat(errors).hasSize(1);
        }
    }
}
//
// @BeforeEach를 이용한 중복 코드 제거 및 테스트 흐름 설명***
//
// 1. **최상위 @BeforeEach**
//  - 이 메서드는 `PasswordVerifier` 클래스의 각 테스트 케이스가 실행되기 전에 한 번씩 호출되어, 새로운 `verifier` 인스턴스를 생성한다
//  - 이는 모든 테스트가 독립적인 환경에서 시작되도록 보장한다.
//
// 2. **중첩 클래스 내 @BeforeEach**
//  - 이 메서드는 `FailingRuleScenario` 그룹에 속한 테스트 메서드(`hasErrorMessageBasedOnRuleReason`와 `hasExactlyOneError`)가 실행되기 전에 한 번씩 호출된다.
//  - 이때, 상위 `@BeforeEach`에서 이미 생성된 `verifier` 인스턴스에 `failingRule`을 추가하여, 해당 시나리오에 맞는 상태를 준비한다.

// ----------------------------------------------------------

// 그럼 결국 시간적으로 봤을 때는 효율성이 똑같은거 아닌가 ? 에 대한 답변

// 네, 맞습니다. @BeforeEach를 사용하면 실행 시간 효율성이 직접적으로 향상되지는 않습니다. 테스트
// 메소드마다 @BeforeEach가 반복 실행되므로, 전체적인 실행 시간은 각 테스트 메소드에 동일한 코드를
// 직접 넣었을 때와 거의 같습니다.
//
//
// 하지만 @BeforeEach를 사용하는 주된 이유는 코드의 가독성, 유지보수성, 그리고 테스트의 명확성을
// 높이는 데 있습니다.
//
// @BeforeEach를 사용함으로써 얻는 이점은 다음과 같습니다.
//
//
//    1. 중복 제거 및 가독성 향상: 여러 테스트에서 공통적으로 필요한 준비 코드를 한 곳에 모아둘 수
//    있습니다. 이렇게 하면 각 테스트 메소드는 검증(assertion)에만 집중할 수 있게 되어 코드가 더
//    간결해지고 읽기 쉬워집니다. "무엇을 테스트하는가"가 명확하게 드러나는 것입니다.
//
//
//    2. 유지보수 용이성: 공통 준비 코드에 변경이 필요할 때, @BeforeEach 메소드만 수정하면 되므로
//    여러 테스트 메소드를 일일이 수정할 필요가 없습니다. 이는 실수를 줄이고 유지보수를 훨씬 쉽게
//    만듭니다.
//
//
//    3. 테스트 의도 명확화: @BeforeEach는 "이 테스트 클래스(또는 중첩 클래스)의 모든 테스트는 이러한
//    사전 조건 하에서 실행된다"는 것을 명확하게 알려줍니다. 이를 통해 테스트의 컨텍스트를 쉽게
//    파악할 수 있습니다.

// -----------------------------------

// 단점도 존재
// beforeEach() 함수와 스크롤 피로감

// @BeforeEach를 사용하면 테스트 코드 자체는 간결해지지만,
// `verifier` 객체나 `rules`가 어디서 초기화디고 어떤 상태를 가지는지 파악하려면
// 코드를 위로 스크롤해야 하는 불편함이 생긴다.

//  **테스트 컨텍스트 파악의 어려움:** `@Test` 메서드만 봐서는 테스트가 어떤 `verifier` 인스턴스와
//  어떤 `rules`를 사용하는지 즉시 알 수 없다.

//  **파일 크기 증가**: 시나리오가 복잡해지고 `@Nested` 구조가 깊어지면,
//  테스트 파일을 위아래로 오가며 전체 맥락을 파악해야 하는 **스크롤 피로감**이 발생한다.
package chap02;

public interface PasswordValidationRule {
    ValidationResult apply(String input);
}

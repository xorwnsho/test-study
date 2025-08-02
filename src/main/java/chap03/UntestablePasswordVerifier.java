package chap03;

import chap02.PasswordValidationRule;
import chap02.ValidationResult;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UntestablePasswordVerifier {

    private final List<PasswordValidationRule> rules;

    public UntestablePasswordVerifier() {
        rules = new ArrayList<>();
    }

    public void addRule(PasswordValidationRule passwordValidationRule) {
        rules.add(passwordValidationRule);
    }

    public List<String> verifyPassword(String input){
        DayOfWeek day = LocalDate.now().getDayOfWeek();

        if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY){
            throw new IllegalArgumentException("It's the weekend!");
        }

        List<String> errors = new ArrayList<>();
        for (PasswordValidationRule rule : rules) {
            ValidationResult result = rule.apply(input);
            if(!result.passed()){
                errors.add("error : " + result.reason());
            }
        }
        return errors;
    }
}

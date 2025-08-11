package chap03;

import chap02.PasswordValidationRule;
import chap02.ValidationResult;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class PasswordVerifierBySupplier {

    private final List<PasswordValidationRule> rules;

    public PasswordVerifierBySupplier(){
        rules = new ArrayList<>();
    }

    public List<String> verifyPassword(String input, Supplier<DayOfWeek> dayOfWeekSupplier){

        DayOfWeek day = dayOfWeekSupplier.get();

        if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY){
            throw new IllegalStateException("It's the weekend!");
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

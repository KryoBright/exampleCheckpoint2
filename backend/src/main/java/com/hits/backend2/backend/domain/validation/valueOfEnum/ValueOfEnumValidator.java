package com.hits.backend2.backend.domain.validation.valueOfEnum;

import com.hits.backend2.backend.domain.validation.valueOfEnum.annotation.ValueOfEnumValidation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnumValidation, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnumValidation annotation) {
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return acceptedValues.contains(value.toString());
    }
}
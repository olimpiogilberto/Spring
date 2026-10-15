package br.com.giba.cadmembros.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CnpjValidator implements ConstraintValidator<CnpjValido, String> {
    private static final int[] WEIGHTS_FIRST = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    private static final int[] WEIGHTS_SECOND = {6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    @Override public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) return true;
        String cnpj = value.replaceAll("\\D", "");
        if (cnpj.length() != 14 || cnpj.chars().distinct().count() == 1) return false;
        int first = digit(cnpj, WEIGHTS_FIRST, 12);
        int second = digit(cnpj, WEIGHTS_SECOND, 13);
        return first == cnpj.charAt(12) - '0' && second == cnpj.charAt(13) - '0';
    }
    private int digit(String value, int[] weights, int length) {
        int sum = 0;
        for (int i = 0; i < length; i++) sum += (value.charAt(i) - '0') * weights[i];
        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }
}

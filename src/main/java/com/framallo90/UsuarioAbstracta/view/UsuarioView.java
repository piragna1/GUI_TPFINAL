package com.framallo90.UsuarioAbstracta.view;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UsuarioView {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

    public static boolean isValidEmail(String emailAddress) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(emailAddress);
        return matcher.matches();
    }

    public static boolean isValidDni(Integer dni){
        // Convert int to String for length check
        String dniString = String.valueOf(dni);
        if (dniString.length() != 8 || !dniString.matches("[0-9]+")) {
            return false;
        }
        return true;
    }

}

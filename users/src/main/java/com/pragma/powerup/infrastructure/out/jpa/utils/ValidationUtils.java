package com.pragma.powerup.infrastructure.out.jpa.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.isEmpty();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\+\\d{1,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isNumericText(String text) {
        try {
            NumberFormat.getInstance().parse(text);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static boolean isStringLengthInRange(String text, int minLength, int maxLength) {
        int length = text.length();
        return length >= minLength && length <= maxLength;
    }

    public static boolean isAdult(LocalDate birthDate) {
        LocalDate currentDate = LocalDate.now();
        LocalDate adultDate = birthDate.plusYears(18);
        return !currentDate.isBefore(adultDate);
    }

}

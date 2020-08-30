package com.amf.registration.validator;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;

public class AMFRegistrationValidator {

    public static void validateBasicInfo(String firstName, String lastName, String userName, String email, int birthdayDay,
                                         int birthdayMonth, int birthdayYear, String password, String confirmPassword) throws AMFRegistrationException {

        validateIsNull(firstName, "First name");
        validateIsNull(lastName, "Last name");
        validateIsNull(userName, "Username");
        validateIsNull(email, "Email");
        validateIsNull(password, "Password");
        validateIsNull(confirmPassword, "Confirm password");

        _validateMaxLength(firstName, "First name", 50);
        _validateMaxLength(lastName, "Last name", 50);
        _validateMaxLength(email, "Email", 255);
        _validateMaxLength(userName, "Username", 16);

        _validateMinLength(userName, "Username", 4);
        _validateBirthday(birthdayDay, birthdayMonth, birthdayYear);

        _validatePassword(password, confirmPassword);
    }

    public static void validateIsNull(String field, String fieldName) throws AMFRegistrationException {
        if (Validator.isNull(field)) {
            throw new AMFRegistrationException(LanguageUtil.format(LocaleThreadLocal.getThemeDisplayLocale(),
                    "field-must-not-be-null", new Object[]{fieldName}));
        }
    }

    private static void _validateMaxLength(String field, String fieldName, int maxLength) throws AMFRegistrationException {
        if (field.length() > maxLength) {
            throw new AMFRegistrationException(LanguageUtil.format(LocaleThreadLocal.getThemeDisplayLocale(),
                    "field-length-must-not-exceed-x-characters", new Object[]{fieldName, maxLength}));
        }
    }

    private static void _validateMinLength(String field, String fieldName, int minLength) throws AMFRegistrationException {
        if (field.length() < minLength) {
            throw new AMFRegistrationException(LanguageUtil.format(LocaleThreadLocal.getThemeDisplayLocale(),
                    "field-length-at-least-x-characters", new Object[]{fieldName, minLength}));
        }
    }

    private static void _validateBirthday(int birthdayDay, int birthdayMonth, int birthdayYear)
            throws AMFRegistrationException {
        LocalDate birthday = LocalDate.of(birthdayYear, birthdayMonth + 1, birthdayDay);
        long age = LocalDate.from(birthday).until(LocalDate.now(), ChronoUnit.YEARS);
        if (age < 13) {
            throw new AMFRegistrationException(LanguageUtil.get(LocaleThreadLocal.getThemeDisplayLocale(),
                    "user-must-at-least-13-to-register"));
        }
    }

    private static void _validatePassword(String password, String confirmPassword)
            throws AMFRegistrationException {

        if (!password.equals(confirmPassword)) {
            throw new AMFRegistrationException(LanguageUtil.get(LocaleThreadLocal.getThemeDisplayLocale(),
                    "password-is-not-match"));
        }
        final Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{6,}$");
        if (!pattern.matcher(password).matches()) {
            throw new AMFRegistrationException(LanguageUtil.get(LocaleThreadLocal.getThemeDisplayLocale(),
                    "invalid-password-policy"));
        }
    }

    public static void validatePhoneNumber(String phoneNumber) throws AMFRegistrationException {
        _validateMaxLength(phoneNumber, "Phone Number", 10);
        _validateMinLength(phoneNumber, "Phone Number", 10);
        if (!Validator.isDigit(phoneNumber)) {
            throw new AMFRegistrationException(LanguageUtil.get(LocaleThreadLocal.getThemeDisplayLocale(),
                    "phone-number-must-be-digits"));
        }
    }


}

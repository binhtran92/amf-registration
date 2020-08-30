package com.amf.registration.validator;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleThreadLocal;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AMFRegistrationValidator {

    public static void validateBasicInfo(String firstName, String lastName, String userName, String email, int birthdayDay,
                                         int birthdayMonth, int birthdayYear, String password, String confirmPassword, ResourceBundle resourceBundle) throws AMFRegistrationException {

        isBlank(firstName, "First name", resourceBundle);
        isBlank(lastName, "Last name", resourceBundle);
        isBlank(userName, "Username", resourceBundle);
        isBlank(email, "Email", resourceBundle);
        isBlank(password, "Password", resourceBundle);
        isBlank(confirmPassword, "Confirm password", resourceBundle);

        _isAlphanumeric(firstName, "First name", resourceBundle);
        _isAlphanumeric(lastName, "Last name", resourceBundle);
        _isAlphanumeric(userName, "Username", resourceBundle);

        _isExceedMaxLength(firstName, "First name", 50, resourceBundle);
        _isExceedMaxLength(lastName, "Last name", 50, resourceBundle);
        _isExceedMaxLength(email, "Email", 255, resourceBundle);
        _isExceedMaxLength(userName, "Username", 16, resourceBundle);

        _isExceedMinLength(userName, "Username", 4, resourceBundle);
        _isValidBirthday(birthdayDay, birthdayMonth, birthdayYear, resourceBundle);

        _isValidPassword(password, confirmPassword, resourceBundle);
        _isValidEmail(email, resourceBundle);
    }

    public static void isBlank(String field, String fieldName, ResourceBundle resourceBundle) throws AMFRegistrationException {
        if (Validator.isNull(field)) {
            throw new AMFRegistrationException(LanguageUtil.format(resourceBundle,
                    "field-must-not-be-null", new Object[]{fieldName}, true));
        }
    }

    private static void _isExceedMaxLength(String field, String fieldName, int maxLength, ResourceBundle resourceBundle) throws AMFRegistrationException {
        if (field.length() > maxLength) {
            throw new AMFRegistrationException(LanguageUtil.format(resourceBundle,
                    "field-length-must-not-exceed-x-characters", new Object[]{fieldName, maxLength}));
        }
    }

    private static void _isExceedMinLength(String field, String fieldName, int minLength, ResourceBundle resourceBundle) throws AMFRegistrationException {
        if (field.length() < minLength) {
            throw new AMFRegistrationException(LanguageUtil.format(resourceBundle,
                    "field-length-at-least-x-characters", new Object[]{fieldName, minLength}));
        }
    }

    private static void _isValidBirthday(int birthdayDay, int birthdayMonth, int birthdayYear, ResourceBundle resourceBundle)
            throws AMFRegistrationException {
        LocalDate birthday = LocalDate.of(birthdayYear, birthdayMonth + 1, birthdayDay);
        long age = LocalDate.from(birthday).until(LocalDate.now(), ChronoUnit.YEARS);
        if (age < 13) {
            throw new AMFRegistrationException(LanguageUtil.get(resourceBundle,
                    "user-must-at-least-13-to-register"));
        }
    }

    private static void _isValidPassword(String password, String confirmPassword, ResourceBundle resourceBundle)
            throws AMFRegistrationException {

        if (!password.equals(confirmPassword)) {
            throw new AMFRegistrationException(LanguageUtil.get(resourceBundle,
                    "password-is-not-match"));
        }
        final Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{6,}$");
        if (!pattern.matcher(password).matches()) {
            throw new AMFRegistrationException(LanguageUtil.get(resourceBundle,
                    "invalid-password-policy"));
        }
    }

    public static void isValidPhoneNumber(String phoneNumber, ResourceBundle resourceBundle) throws AMFRegistrationException {
        if (phoneNumber.length() != 10) {
            throw new AMFRegistrationException(LanguageUtil.get(resourceBundle,
                    "invalid-phone-number"));
        }
        if (!Validator.isDigit(phoneNumber)) {
            throw new AMFRegistrationException(LanguageUtil.get(resourceBundle,
                    "phone-number-must-be-digits"));
        }
    }

    private static void _isAlphanumeric(String field, String fieldName, ResourceBundle resourceBundle) throws AMFRegistrationException {
        if (Validator.isNull(field)) {
            throw new AMFRegistrationException(LanguageUtil.format(resourceBundle,
                    "field-must-contains-only-alphanumeric-characters", new Object[]{fieldName}));
        }
    }

    private static void _isValidEmail(String email, ResourceBundle resourceBundle) throws AMFRegistrationException {
        if (Validator.isNull(email)) {
            throw new AMFRegistrationException(LanguageUtil.get(resourceBundle,
                    "invalid-email-address"));
        }
    }
}

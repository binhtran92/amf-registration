package com.amf.registration.validator;

import com.amf.registration.exception.RegistrationValidationException;
import com.amf.registration.exception.UserExtraInfoException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.service.RegionServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.ResourceBundleUtil;
import com.liferay.portal.kernel.util.Validator;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static com.liferay.portal.kernel.language.LanguageUtil.get;

public class AMFRegistrationValidator {

    public static void validateBasicInfo(String firstName, String lastName, String userName, String email, int birthdayDay,
                                         int birthdayMonth, int birthdayYear, String password, String confirmPassword, ResourceBundle resourceBundle) throws RegistrationValidationException {

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

    public static void validateAddressInfo(String address, String address2, String city, int state, String zipcode, ResourceBundle resourceBundle) throws RegistrationValidationException, UserExtraInfoException {

        isBlank(address, "Address", resourceBundle);
        isBlank(city, "City", resourceBundle);
        isBlank(zipcode, "Zipcode", resourceBundle);

        _isExceedMaxLength(address, "Address", 255, resourceBundle);
        if (!Validator.isBlank(address2)) {
            _isExceedMaxLength(address2, "Address", 255, resourceBundle);
        }
        _isExceedMaxLength(city, "City", 255, resourceBundle);
        _validateState(state, resourceBundle);
        _validateZipCode(zipcode, resourceBundle);
    }

    public static void isBlank(String field, String fieldName, ResourceBundle resourceBundle) throws RegistrationValidationException {
        if (Validator.isNull(field)) {
            throw new RegistrationValidationException(LanguageUtil.format(resourceBundle,
                    "field-must-not-be-null", new Object[]{fieldName}, true));
        }
    }

    private static void _isExceedMaxLength(String field, String fieldName, int maxLength, ResourceBundle resourceBundle) throws RegistrationValidationException {
        if (field.length() > maxLength) {
            throw new RegistrationValidationException(LanguageUtil.format(resourceBundle,
                    "field-length-must-not-exceed-x-characters", new Object[]{fieldName, maxLength}));
        }
    }

    private static void _isExceedMinLength(String field, String fieldName, int minLength, ResourceBundle resourceBundle) throws RegistrationValidationException {
        if (field.length() < minLength) {
            throw new RegistrationValidationException(LanguageUtil.format(resourceBundle,
                    "field-length-at-least-x-characters", new Object[]{fieldName, minLength}));
        }
    }

    private static void _isValidBirthday(int birthdayDay, int birthdayMonth, int birthdayYear, ResourceBundle resourceBundle)
            throws RegistrationValidationException {
        LocalDate birthday = LocalDate.of(birthdayYear, birthdayMonth + 1, birthdayDay);
        long age = LocalDate.from(birthday).until(LocalDate.now(), ChronoUnit.YEARS);
        if (age < 13) {
            throw new RegistrationValidationException(LanguageUtil.get(resourceBundle,
                    "user-must-at-least-13-to-register"));
        }
    }

    private static void _isValidPassword(String password, String confirmPassword, ResourceBundle resourceBundle)
            throws RegistrationValidationException {

        if (!password.equals(confirmPassword)) {
            throw new RegistrationValidationException(LanguageUtil.get(resourceBundle,
                    "password-is-not-match"));
        }
        final Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{6,}$");
        if (!pattern.matcher(password).matches()) {
            throw new RegistrationValidationException(LanguageUtil.get(resourceBundle,
                    "invalid-password-policy"));
        }
    }

    public static void isValidPhoneNumber(String phoneNumber, ResourceBundle resourceBundle) throws RegistrationValidationException {
        if (phoneNumber.length() != 10) {
            throw new RegistrationValidationException(LanguageUtil.get(resourceBundle,
                    "invalid-phone-number"));
        }
        if (!Validator.isDigit(phoneNumber)) {
            throw new RegistrationValidationException(LanguageUtil.get(resourceBundle,
                    "phone-number-must-be-digits"));
        }
    }

    private static void _isAlphanumeric(String field, String fieldName, ResourceBundle resourceBundle) throws RegistrationValidationException {
        if (Validator.isNull(field)) {
            throw new RegistrationValidationException(LanguageUtil.format(resourceBundle,
                    "field-must-contains-only-alphanumeric-characters", new Object[]{fieldName}));
        }
    }

    private static void _isValidEmail(String email, ResourceBundle resourceBundle) throws RegistrationValidationException {
        if (Validator.isNull(email)) {
            throw new RegistrationValidationException(LanguageUtil.get(resourceBundle,
                    "invalid-email-address"));
        }
    }

    private static void _validateState(int state, ResourceBundle resourceBundle) throws UserExtraInfoException {
        try {
            final Region region = RegionServiceUtil.getRegion(state);
            final long countryId = region.getCountryId();
            if (countryId != US_COUNTRY_ID) {
                throw new UserExtraInfoException(get(resourceBundle, "state-code-is-invalid"));
            }
        } catch (PortalException ex) {
            throw new UserExtraInfoException(get(resourceBundle, "state-code-is-invalid"));
        }
    }

    private static void _validateZipCode(String zipcode, ResourceBundle resourceBundle) throws RegistrationValidationException {
        if (zipcode.length() != 5) {
            throw new RegistrationValidationException(get(resourceBundle, "zipcode-must-have-5-digits"));
        }
    }

    private static final long US_COUNTRY_ID = 19;
}

package com.erfanlibrary.api.validator;

import com.erfanlibrary.api.exception.user.InvalidUserEmailException;
import com.erfanlibrary.api.exception.user.InvalidUserPasswordException;
import com.erfanlibrary.api.exception.user.InvalidUserPhoneNumberException;
import com.erfanlibrary.api.interfaces.Validator;
import com.erfanlibrary.api.model.User;
import com.erfanlibrary.api.util.message.user.UserExceptionMessage;
import org.springframework.stereotype.Component;

@Component
public class UserValidator implements Validator<User> {

    public void validate(User user) {
        this.validateCustomerEmail(user.getEmail());
        this.validateCustomerPhone(user.getPhoneNumber());
        this.validateCustomerPassword(user.getPassword());

    }

    private void validateCustomerEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new InvalidUserEmailException(UserExceptionMessage.EMAIL_NULL_OR_EMPTY_ERROR);
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new InvalidUserEmailException(UserExceptionMessage.EMAIL_INVALID_FORMAT_ERROR);
        }
    }

    private void validateCustomerPhone(String phone) {
        if (phone == null || phone.isEmpty()) {
            throw new InvalidUserPhoneNumberException(UserExceptionMessage.PHONE_NULL_OR_EMPTY_ERROR);
        }
        if (!phone.matches("^[0-9]{10,15}$")) {
            throw new InvalidUserPhoneNumberException(UserExceptionMessage.PHONE_INVALID_FORMAT_ERROR);
        }
    }

    private void validateCustomerPassword(String password) {
        if (password == null || password.isEmpty()) {
            throw new InvalidUserPasswordException(UserExceptionMessage.PASSWORD_NULL_OR_EMPTY_ERROR);
        }
        if (password.length() < 8) {
            throw new InvalidUserPasswordException(UserExceptionMessage.PASSWORD_TOO_SHORT_ERROR);
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new InvalidUserPasswordException(UserExceptionMessage.PASSWORD_NO_UPPERCASE_ERROR);
        }
        if (!password.matches(".*[a-z].*")) {
            throw new InvalidUserPasswordException(UserExceptionMessage.PASSWORD_NO_LOWERCASE_ERROR);
        }
        if (!password.matches(".*[0-9].*")) {
            throw new InvalidUserPasswordException(UserExceptionMessage.PASSWORD_NO_DIGIT_ERROR);
        }
    }

}

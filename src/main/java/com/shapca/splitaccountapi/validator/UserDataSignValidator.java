package com.shapca.splitaccountapi.validator;

import com.shapca.splitaccountapi.form.UserData;
import com.shapca.splitaccountapi.service.UserService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserDataSignValidator implements Validator {

    private final UserService userService;

    public UserDataSignValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserData.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (!errors.hasErrors()) {
            UserData userData = (UserData) target;
            if (userService.sign(userData) == null) {
                errors.rejectValue("login", "login.not-vacant", "login or password is incorrect");
            }
        }
    }
}
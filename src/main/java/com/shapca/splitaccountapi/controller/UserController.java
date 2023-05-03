package com.shapca.splitaccountapi.controller;

import com.shapca.splitaccountapi.domain.User;
import com.shapca.splitaccountapi.exception.ValidationException;
import com.shapca.splitaccountapi.form.UserData;
import com.shapca.splitaccountapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/1/")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@Valid @RequestBody UserData registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        return userService.register(registerForm);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public String sign(@RequestBody @Valid UserData userData, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        return userService.sign(userData);
    }

    @GetMapping("/users/{jwt}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserByJwt(@PathVariable String jwt) {
        return userService.findByJWT(jwt);
    }
}

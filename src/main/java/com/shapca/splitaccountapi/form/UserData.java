package com.shapca.splitaccountapi.form;


import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserData {

    @NotNull
    @NotEmpty
    @Size(min = 2, max = 16)
    @Pattern(regexp = "[a-z]+", message = "Only small latin letters")
    private String login;
    @NotNull
    @NotEmpty
    @Size(min = 2, max = 16)
    private String password;

}

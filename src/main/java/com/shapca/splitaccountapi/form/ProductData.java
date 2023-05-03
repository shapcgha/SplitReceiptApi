package com.shapca.splitaccountapi.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class ProductData {
    @NotNull
    @PositiveOrZero
    long price;

    @NotNull
    @PositiveOrZero
    long count;

    @NotNull
    @NotEmpty
    String name;

    @NotNull
    boolean isOwn;

}

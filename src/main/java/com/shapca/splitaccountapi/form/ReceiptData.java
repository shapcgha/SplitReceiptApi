package com.shapca.splitaccountapi.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReceiptData {
    @NotNull
    @NotEmpty
    private String name;
}

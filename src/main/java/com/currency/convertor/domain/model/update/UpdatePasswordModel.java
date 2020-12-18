package com.currency.convertor.domain.model.update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdatePasswordModel {

    @NotBlank
    @Size(min = 3, max = 20)
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

package com.currency.convertor.domain.model.update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UpdateUsernameModel {

    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}

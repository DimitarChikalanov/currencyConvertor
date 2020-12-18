package com.currency.convertor.domain.model.update;

import javax.validation.constraints.Email;

public class UpdateEmailModel {

    @Email
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

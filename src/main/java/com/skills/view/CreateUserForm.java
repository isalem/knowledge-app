package com.skills.view;

import com.skills.domain.model.UserRole;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

public class CreateUserForm {

    @NotNull
    @NotEmpty(message = "{NotEmpty.name}")
    @Length(max = 100, message = "{Length.name}")
    private String name;

    @NotNull
    @NotEmpty(message = "{NotEmpty.email}")
    @Email(message = "{Email.email}")
    @Length(max = 100, message = "{Length.email}")
    private String email;

    @NotNull
    @NotEmpty(message = "{NotEmpty.username}")
    @Length(max = 30, message = "{Length.username}")
    private String username;

    @NotNull
    @NotEmpty(message = "{NotEmpty.password}")
    @Length(min = 6, message = "{Length.password}")
    private String password;

    private UserRole authoritie;

    public CreateUserForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getAuthoritie() {
        return authoritie;
    }

    public void setAuthoritie(UserRole authoritie) {
        this.authoritie = authoritie;
    }
}

package com.github.ankurpathak.isobarwallet.controller.rest.dto;

import com.github.ankurpathak.isobarwallet.domain.model.Account;
import com.github.ankurpathak.password.bean.constraints.PasswordMatches;
import com.github.ankurpathak.primitive.bean.constraints.string.Contact;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.math.BigInteger;

@PasswordMatches
public class AccountDto extends DomainDto<Account, BigInteger> {

    public static AccountDto getInstance(){
        return new AccountDto();
    }

    @NotBlank
    private String name;

    @NotBlank
    @Contact
    private String phone;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String password;


    private String address;



    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String confirmPassword;




    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public AccountDto name(String name) {
        this.name = name;
        return this;
    }

    public AccountDto phone(String phone) {
        this.phone = phone;
        return this;
    }

    public AccountDto email(String email) {
        this.email = email;
        return this;
    }

    public AccountDto password(String password) {
        this.password = password;
        return this;
    }

    public AccountDto confirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }

    public AccountDto address(String address) {
        this.address = address;
        return this;
    }
}

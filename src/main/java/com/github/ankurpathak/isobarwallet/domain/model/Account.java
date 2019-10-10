package com.github.ankurpathak.isobarwallet.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.ankurpathak.isobarwallet.controller.rest.dto.AccountDto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

@Entity
@Table(name = "account")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Account extends Domain<BigInteger>  {

    private BigDecimal amount = BigDecimal.ZERO;

    public Account addFunds(BigDecimal amount){
        if(Objects.isNull(this.amount)){
            this.amount = BigDecimal.ZERO;
        }
        if(Objects.nonNull(amount)){
            this.amount = this.amount.add(amount);
        }
        return this;
    }

    public Account removeFunds(BigDecimal amount){
        if(Objects.isNull(this.amount)){
            this.amount = BigDecimal.ZERO;
        }
        if(Objects.nonNull(amount)){
            this.amount = this.amount.subtract(amount);
        }
        return this;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
                                @JsonIgnore
    private String name, phone, password, email, address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return phone;
    }

    public void setMobile(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public Account name(String name) {
        this.name = name;
        return this;
    }

    public Account phone(String phone) {
        this.phone = phone;
        return this;
    }

    public Account password(String password) {
        this.password = password;
        return this;
    }

    public Account email(String email) {
        this.email = email;
        return this;
    }

    public Account address(String address) {
        this.address = address;
        return this;
    }

    public Account amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public static Account getInstance(){
        return new Account();
    }

    public static Account getInstance(AccountDto accountDto){
        return getInstance()
                .name(accountDto.getName())
                .email(accountDto.getEmail())
                .phone(accountDto.getPhone())
                .password(accountDto.getPassword())
                .address(accountDto.getAddress());

    }
}

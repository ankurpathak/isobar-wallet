package com.github.ankurpathak.isobarwallet.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Table(name = "account_ledger")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AccountLedger extends Domain<BigInteger> {

    private BigDecimal amount;

    private String note;

    @Enumerated(EnumType.STRING)
    private AccountLedgerEntryType type = AccountLedgerEntryType.CREDIT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    public AccountLedgerEntryType getType() {
        return type;
    }

    public void setType(AccountLedgerEntryType type) {
        this.type = type;
    }

    public AccountLedger type(AccountLedgerEntryType type) {
        this.type = type;
        return this;
    }

    public enum AccountLedgerEntryType {
        CREDIT, DEBIT
    }

    public static AccountLedger getInstance(){
        return new AccountLedger();
    }


    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountLedger amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public AccountLedger note(String note) {
        this.note = note;
        return this;
    }

    public AccountLedger account(Account account) {
        this.account = account;
        return this;
    }
}

package com.github.ankurpathak.isobarwallet.service;

import com.github.ankurpathak.isobarwallet.domain.model.Account;
import com.github.ankurpathak.isobarwallet.domain.model.AccountLedger;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.util.List;

public interface IAccountService extends IDomainService<Account, BigInteger> {
    void addFunds(BigInteger id, BigDecimal amount);
    void removeFunds(BigInteger id, BigDecimal amount);

    List<AccountLedger> recentAccountLedger(BigInteger id);

    Object accountLedgerInInterval(BigInteger id, Instant fromDate, Instant toDate);
}
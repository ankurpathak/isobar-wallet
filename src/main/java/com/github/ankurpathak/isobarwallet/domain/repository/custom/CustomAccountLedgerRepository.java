package com.github.ankurpathak.isobarwallet.domain.repository.custom;

import com.github.ankurpathak.isobarwallet.domain.model.Account;
import com.github.ankurpathak.isobarwallet.domain.model.AccountLedger;

import java.math.BigInteger;

public interface CustomAccountLedgerRepository extends ICustomizedDomainRepository<AccountLedger, BigInteger> {
}

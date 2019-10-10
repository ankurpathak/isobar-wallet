package com.github.ankurpathak.isobarwallet.domain.repository;

import com.github.ankurpathak.isobarwallet.domain.model.AccountLedger;
import com.github.ankurpathak.isobarwallet.domain.repository.custom.CustomAccountLedgerRepository;

import java.math.BigInteger;

public interface IAccountLedgerRepository extends ExtendedJpaRepository<AccountLedger, BigInteger>, CustomAccountLedgerRepository  {
}

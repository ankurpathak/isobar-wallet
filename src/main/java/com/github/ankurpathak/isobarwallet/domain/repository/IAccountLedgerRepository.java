package com.github.ankurpathak.isobarwallet.domain.repository;

import com.github.ankurpathak.isobarwallet.domain.model.Account;
import com.github.ankurpathak.isobarwallet.domain.model.AccountLedger;
import com.github.ankurpathak.isobarwallet.domain.repository.custom.CustomAccountLedgerRepository;
import com.github.ankurpathak.isobarwallet.domain.repository.custom.CustomAccountRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.math.BigInteger;

public interface IAccountLedgerRepository extends ExtendedJpaRepository<AccountLedger, BigInteger>, CustomAccountLedgerRepository  {
}
